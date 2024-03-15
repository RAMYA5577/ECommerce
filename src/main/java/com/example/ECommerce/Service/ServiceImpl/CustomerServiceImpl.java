package com.example.ECommerce.Service.ServiceImpl;

import com.example.ECommerce.Dto.RequestDto.CustomerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CustomerResponseDto;
import com.example.ECommerce.Enum.Gender;
import com.example.ECommerce.Model.Cart;
import com.example.ECommerce.Model.Customer;
import com.example.ECommerce.Repository.CustomerRepository;
import com.example.ECommerce.Service.CustomerService;
import com.example.ECommerce.Transformer.CustomerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Autowired
    CustomerRepository customerRepository;

    @Override
    public CustomerResponseDto addCustomer(CustomerRequestDto customerRequestDto) {
        //dto to entity
        Customer customer = CustomerTransformer.customerRequestDtoToCustomer(customerRequestDto);
        Cart cart = Cart.builder()
                .customer(customer)
                .cartTotal(0)
                .build();
        customer.setCart(cart);

        //save
        Customer savedCustomer = customerRepository.save(customer);
        //entity to dto
        CustomerResponseDto customerResponseDto = CustomerTransformer.customerToCustomerResponseDto(savedCustomer);
        return customerResponseDto;
    }

    @Override
    public List<String> getAllFemalesAgeBetween20to30() {
        List<String> femaleCustomers=new ArrayList<>();
        List<Customer> customers=customerRepository.findAll();
        for(Customer customer:customers){
            if(customer.getGender().equals(Gender.FEMALE) && customer.getAge()>=20 && customer.getAge()<=30)
                femaleCustomers.add(customer.getName());
        }
        return femaleCustomers;
    }

    @Override
    public List<String> getMaleCustomersAgeBelow45() {
        List<String> maleCustomers=new ArrayList<>();
        List<Customer> customers=customerRepository.findAll();
        for(Customer customer:customers){
            if(customer.getGender().equals(Gender.MALE) && customer.getAge()<=45){
                maleCustomers.add(customer.getName());
            }
        }
        return maleCustomers;
    }

    @Override
    public List<String> customersWithAtLeastKOrders(int k) {
        List<String> customersWithKOrders=new ArrayList<>();
        List<Customer> customers=customerRepository.findAll();
        for(Customer customer:customers){
            if(customer.getOrders().size()>=k){
                customersWithKOrders.add(customer.getName());
            }
        }
        return customersWithKOrders;
    }

}