package com.example.ECommerce.Transformer;

import com.example.ECommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.ECommerce.Model.Cart;
import com.example.ECommerce.Model.Item;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;

@UtilityClass
public class CartTransformer {

    public static CartResponseDto cartToCartResponseDto(Cart cart){

        List<ItemResponseDto> itemResponseDtos = new ArrayList<>();
        for(Item item: cart.getItems()){
            itemResponseDtos.add(ItemTransformer.itemToItemResponseDto(item));
        }

        return CartResponseDto.builder()
                .cartTotal(cart.getCartTotal())
                .customerName(cart.getCustomer().getName())
                .items(itemResponseDtos)
                .build();
    }
}
