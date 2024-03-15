package com.example.ECommerce.Service;

import com.example.ECommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CustomerResponseDto;

import java.util.List;

public interface CustomerService {
    CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto);

    List<String> getAllFemalesAgeBetween20to30();

    List<String> getMaleCustomersAgeBelow45();

    List<String> customersWithAtLeastKOrders(int k);
}
