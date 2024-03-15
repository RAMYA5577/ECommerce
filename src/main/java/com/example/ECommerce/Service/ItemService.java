package com.example.ECommerce.Service;

import com.example.ECommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Exception.InsufficientQuantityException;
import com.example.ECommerce.Exception.OutOfStockException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Model.Item;

public interface ItemService {

    Item createItem(ItemRequestDto itemRequestDto) throws ProductNotFoundException, CustomerNotFoundException, OutOfStockException, InsufficientQuantityException;
}
