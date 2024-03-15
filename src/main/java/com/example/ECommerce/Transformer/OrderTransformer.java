package com.example.ECommerce.Transformer;

import com.example.ECommerce.Dto.ResponseDto.ItemResponseDto;
import com.example.ECommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Model.Customer;
import com.example.ECommerce.Model.Item;
import com.example.ECommerce.Model.OrderEntity;
import lombok.experimental.UtilityClass;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@UtilityClass
public class OrderTransformer {
    public static OrderEntity orderRequestDtoToOrder(Item item, Customer customer){

        return OrderEntity.builder()
                .orderNo(String.valueOf(UUID.randomUUID()))
                .customer(customer)
                .items(new ArrayList<>())
                .totalValue(item.getRequiredQuantity()*item.getProduct().getPrice())
                .build();
    }

    public static OrderResponseDto orderToOrderResponseDto(OrderEntity orderEntity){

        List<ItemResponseDto> itemResponseDtoList = new ArrayList<>();
        for(Item item: orderEntity.getItems()){
            itemResponseDtoList.add(ItemTransformer.itemToItemResponseDto(item));
        }

        return OrderResponseDto.builder()
                .orderDate(orderEntity.getDateOfOrder())
                .cardUsed(orderEntity.getCardUsed())
                .customerName(orderEntity.getCustomer().getName())
                .totalValue(orderEntity.getTotalValue())
                .orderNo(orderEntity.getOrderNo())
                .items(itemResponseDtoList)
                .build();
    }
}
