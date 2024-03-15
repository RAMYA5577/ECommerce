package com.example.ECommerce.Controller;

import com.example.ECommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.Service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.ReactiveOffsetScrollPositionHandlerMethodArgumentResolver;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("customer")
public class CustomerController {
    @Autowired
    CustomerService customerService;

    //1
    //add customer
    @PostMapping("/add")
    public ResponseEntity addCustomer(@RequestBody CustomerRequestDto customerRequestDto){
        CustomerResponseDto customerResponseDto=customerService.addCustomer(customerRequestDto);
        return new ResponseEntity<>(customerResponseDto, HttpStatus.CREATED);
    }

    //2
    // get all female customers between age 20-30
    @GetMapping("/get_all_females_age_btw_20-30")
    public ResponseEntity getAllFemalesAgeBetween20to30(){
        List<String> femaleCustomers=customerService.getAllFemalesAgeBetween20to30();
        return new ResponseEntity<>(femaleCustomers,HttpStatus.FOUND);
    }

    // get all male customers age less than 45
    @GetMapping("/get_male_customers_age_below_45")
    public ResponseEntity getMaleCustomersAgeBelow45(){
        List<String> maleCustomersAAgeBelow45=customerService.getMaleCustomersAgeBelow45();
        return new ResponseEntity<>(maleCustomersAAgeBelow45,HttpStatus.FOUND);
    }

    // customers who have ordered atleast 'k' orders
    @GetMapping("/customers_min_k_orders")
    public ResponseEntity customersWithAtLeastKOrders(int k){
        List<String> customersWithKOrders=customerService.customersWithAtLeastKOrders(k);
        return new ResponseEntity<>(customersWithKOrders,HttpStatus.FOUND);
    }
}
