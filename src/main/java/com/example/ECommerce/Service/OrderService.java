package com.example.ECommerce.Service;

import com.example.ECommerce.Dto.RequestDto.OrderRequestDto;
import com.example.ECommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Exception.InsufficientQuantityException;
import com.example.ECommerce.Exception.InvalidCardException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Model.Card;
import com.example.ECommerce.Model.Cart;
import com.example.ECommerce.Model.OrderEntity;

public interface OrderService {
    OrderResponseDto placeOrder(OrderRequestDto orderRequestDto) throws CustomerNotFoundException, ProductNotFoundException,
            InsufficientQuantityException, InvalidCardException;
    OrderEntity placeOrder(Cart cart, Card card) throws InsufficientQuantityException;
}
