package com.example.ECommerce.Service;

import com.example.ECommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Exception.*;
import com.example.ECommerce.Model.Item;

public interface CartService {
    CartResponseDto addToCart(Item item, ItemRequestDto itemRequestDto) ;
    OrderResponseDto checkOutCart(CheckoutCartRequestDto checkoutCartRequestDto) throws CustomerNotFoundException, InvalidCardException, EmptyCartException, InsufficientQuantityException;
}
