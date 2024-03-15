package com.example.ECommerce.Exception;

import com.example.ECommerce.Model.Product;
import com.example.ECommerce.Repository.ProductRepository;

public class ProductNotFoundException extends Exception{
    public ProductNotFoundException(String message){
        super(message);
    }
}
