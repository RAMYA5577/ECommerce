package com.example.ECommerce.Transformer;

import com.example.ECommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.Model.Customer;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CustomerTransformer {
    public static Customer customerRequestDtoToCustomer(CustomerRequestDto customerRequestDto){
        return Customer.builder()
                .name(customerRequestDto.getName())
                .age(customerRequestDto.getAge())
                .mobNo(customerRequestDto.getMobNo())
                .emailId(customerRequestDto.getEmailId())
                .gender(customerRequestDto.getGender())
                .build();
    }

    public static CustomerResponseDto customerToCustomerResponseDto(Customer customer){
        return CustomerResponseDto.builder()
                .message("Customer Added Successfully!!")
                .name(customer.getName())
                .age(customer.getAge())
                .mobNo(customer.getMobNo())
                .emailId(customer.getEmailId())
                .gender(customer.getGender())
                .build();
    }
}
