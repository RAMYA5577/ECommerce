
package com.example.ECommerce.Service.ServiceImpl;

import com.example.ECommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Exception.*;
import com.example.ECommerce.Model.*;
import com.example.ECommerce.Repository.*;
import com.example.ECommerce.Service.CartService;
import com.example.ECommerce.Service.OrderService;
import com.example.ECommerce.Transformer.CartTransformer;
import com.example.ECommerce.Transformer.OrderTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    CardRepository cardRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Autowired
    OrderService orderService;
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private JavaMailSender emailSender;


    @Override
    public CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) {
        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        Product product = productRepository.findById(itemRequestDto.getProductId()).get();

        Cart cart = customer.getCart();
        cart.setCartTotal(cart.getCartTotal()+item.getRequiredQuantity()*product.getPrice());
        cart.getItems().add(item);
        item.setCart(cart);
        item.setProduct(product);

        Cart savedCart = cartRepository.save(cart);  // saves both cart and item
        Item savedItem = cart.getItems().get(cart.getItems().size()-1);
        product.getItems().add(savedItem);
        //prepare response dto
        return CartTransformer.cartToCartResponseDto(savedCart);
    }

    public OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto)
            throws CustomerNotFoundException, InvalidCardException, EmptyCartException,
            InsufficientQuantityException {

        Customer customer=customerRepository.findByEmailId(checkoutCartRequestDto.getEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }
        Card card=cardRepository.findByCardNo(checkoutCartRequestDto.getCardNo());
        Date date=new Date();
        if(card==null || card.getCvv()!= checkoutCartRequestDto.getCvv() || date.after(card.getValidTill())){
            throw new InvalidCardException("Sorry! you can't use this card!!");
        }
        Cart cart=customer.getCart();
        if(cart.getItems().size()==0){
            throw new EmptyCartException("Cart is empty!!");
        }
        try{
            OrderEntity order=orderService.placeOrder(cart,card);
            resetCart(cart);
            OrderEntity savedOrder=orderRepository.save(order);
            customer.getOrders().add(savedOrder);

            //send email
            SimpleMailMessage message = new SimpleMailMessage();
            message.setFrom("testifydosify@gmail.com");
            message.setTo(customer.getEmailId());
            message.setSubject("Alert!!!");
            message.setText("Dear Customer!! Your Order has been placed successfully!! " +OrderTransformer.orderToOrderResponseDto(savedOrder)+ "Thank you!!");
            emailSender.send(message);
            return OrderTransformer.orderToOrderResponseDto(savedOrder);
        }
        catch(InsufficientQuantityException e){
            throw new InsufficientQuantityException("Insufficient Quantity!!");
        }
    }

    private void resetCart(Cart cart){

        cart.setCartTotal(0);
        for(Item item: cart.getItems())
            item.setCart(null);
        cart.setItems(new ArrayList<>());
    }

}
