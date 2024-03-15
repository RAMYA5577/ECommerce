package com.example.ECommerce.Service.ServiceImpl;

import com.example.ECommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Exception.InsufficientQuantityException;
import com.example.ECommerce.Exception.OutOfStockException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Model.Customer;
import com.example.ECommerce.Model.Item;
import com.example.ECommerce.Model.Product;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Service.ItemService;
import com.example.ECommerce.Transformer.ItemTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ItemServiceImpl implements ItemService {
    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;
    @Override
    public Item createItem(ItemRequestDto itemRequestDto)
            throws ProductNotFoundException, CustomerNotFoundException, OutOfStockException, InsufficientQuantityException {
        Optional<Product> productOptional = productRepository.findById(itemRequestDto.getProductId());
        if(productOptional.isEmpty()){
            throw new ProductNotFoundException("Product doesn't exist");
        }

        Customer customer = customerRepository.findByEmailId(itemRequestDto.getCustomerEmailId());
        if(customer==null){
            throw new CustomerNotFoundException("Customer doesn't exist");
        }

        Product product = productOptional.get();
        if(product.getQuantity()==0){
            throw new OutOfStockException("Product is out of stock");
        }
        if(product.getQuantity()<itemRequestDto.getRequiredQuantity()){
            throw new InsufficientQuantityException("Sorry! The required quantity is not avaiable");
        }

        Item item = ItemTransformer.itemRequestDtoToItem(itemRequestDto.getRequiredQuantity());
        return item;
    }
}
