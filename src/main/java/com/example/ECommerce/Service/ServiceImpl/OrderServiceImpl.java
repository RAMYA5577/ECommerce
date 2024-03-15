package com.example.ECommerce.Service.ServiceImpl;

import com.example.ECommerce.Dto.RequestDto.OrderRequestDto;
import com.example.ECommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Enum.ProductStatus;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Exception.InsufficientQuantityException;
import com.example.ECommerce.Exception.InvalidCardException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Model.*;
import com.example.ECommerce.Repository.CardRepository;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Repository.OrderRepository;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Service.OrderService;
import com.example.ECommerce.Transformer.ItemTransformer;
import com.example.ECommerce.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;
import java.util.UUID;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    CardRepository cardRepository;

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    private JavaMailSender emailSender;

    @Override
    public OrderResponseDto placeOrder(OrderRequestDto orderRequestDto)
            throws CustomerNotFoundException, ProductNotFoundException,
            InsufficientQuantityException, InvalidCardException {

        Customer customer = customerRepository.findByEmailId(orderRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Optional<Product> optionalProduct = productRepository.findById(orderRequestDto.getProductId());
        if(optionalProduct.isEmpty()){
            throw new ProductNotFoundException("Sorry! Product doesn't exist");
        }

        Product product = optionalProduct.get();
        // check quantity
        if(product.getQuantity()<orderRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Required quantity not available");
        }

        // card
        Card card = cardRepository.findByCardNo(orderRequestDto.getCardNo());
        Date date = new Date();
        if(card==null || card.getCvv()!= orderRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Sorry! You can't use this card!");
        }

        int newQuantity = product.getQuantity() - orderRequestDto.getRequiredQuantity();
        product.setQuantity(newQuantity);
        if(newQuantity==0){
            product.setProductStatus(ProductStatus.OUT_OF_STOCK);

            //send email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("testifydosify@gmail.com");
            message.setTo(product.getSeller().getEmailId());
            message.setSubject("Alert!!!");
            message.setText("Hey, Dealer!! Your Product is Out Of Stock!!, Make sure to add more products to maintain consistency in the market!!");
            emailSender.send(message);
        }

        // Create Item
        Item item = ItemTransformer.itemRequestDtoToItem(orderRequestDto.getRequiredQuantity());
        item.setProduct(product);

        // create order
        OrderEntity orderEntity = OrderTransformer.orderRequestDtoToOrder(item,customer);

        String maskedCard = generateMaskedCardNo(card);
        orderEntity.setCardUsed(maskedCard);

        orderEntity.getItems().add(item);
        item.setOrderEntity(orderEntity);

        OrderEntity savedOrder = orderRepository.save(orderEntity);  // saves both order and item

        customer.getOrders().add(savedOrder);
        product.getItems().add(savedOrder.getItems().get(0));

        // prepare response dto
        return OrderTransformer.orderToOrderResponseDto(savedOrder);
    }

    @Override
    public OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException {
        OrderEntity orderEntity = new OrderEntity();
        orderEntity.setOrderNo(String.valueOf(UUID.randomUUID()));
        orderEntity.setCardUsed(generateMaskedCardNo(card));

        int totalValue = 0;
        for(Item item: cart.getItems()){
            Product product = item.getProduct();
            if(item.getRequiredQuantity()>product.getQuantity()){
                throw new InsufficientQuantityException("Required Quantity not present!!");
            }

            totalValue += item.getRequiredQuantity()*product.getPrice();
            int newQuantity = product.getQuantity()-item.getRequiredQuantity();
            product.setQuantity(newQuantity);
            if(newQuantity==0){
                product.setProductStatus(ProductStatus.OUT_OF_STOCK);

                //send email
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom("testifydosify@gmail.com");
                message.setTo(product.getSeller().getEmailId());
                message.setSubject("Alert!!!");
                message.setText("Hey, Dealer!! Your Product is Out Of Stock!!, Make sure to add more products to maintain consistency in the market!!");
                emailSender.send(message);
            }
            item.setOrderEntity(orderEntity);
        }

        orderEntity.setTotalValue(totalValue);
        orderEntity.setItems(cart.getItems());
        orderEntity.setCustomer(cart.getCustomer());

        return orderEntity;
    }

    private String generateMaskedCardNo(Card card){
        String cardNo = "";
        String originalCardNo = card.getCardNo();

        for(int i=0;i<originalCardNo.length()-4;i++){
            cardNo += "X";
        }
        cardNo += originalCardNo.substring(originalCardNo.length()-4);
        return cardNo;
    }
}
