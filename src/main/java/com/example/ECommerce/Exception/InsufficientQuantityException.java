package com.example.ECommerce.Exception;

public class InsufficientQuantityException extends Exception{
    public InsufficientQuantityException(String message){
        super(message);
    }
}
