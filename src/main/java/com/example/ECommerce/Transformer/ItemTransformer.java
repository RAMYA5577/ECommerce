package com.example.ECommerce.Transformer;

import com.example.ECommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.ECommerce.Model.Item;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ItemTransformer {
    public static Item itemRequestDtoToItem(int quantity){

        return Item.builder()
                .requiredQuantity(quantity)
                .build();
    }

    public static ItemResponseDto itemToItemResponseDto(Item item) {

        return ItemResponseDto.builder()
                .quantityAdded(item.getRequiredQuantity())
                .productName(item.getProduct().getName())
                .price(item.getProduct().getPrice())
                .build();
    }
}
