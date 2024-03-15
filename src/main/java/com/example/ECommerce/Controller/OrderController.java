package com.example.ECommerce.Controller;

import com.example.ECommerce.Dto.RequestDto.OrderRequestDto;
import com.example.ECommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Exception.InsufficientQuantityException;
import com.example.ECommerce.Exception.InvalidCardException;
import com.example.ECommerce.Exception.ProductNotFoundException;
import com.example.ECommerce.Service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
public class OrderController {
    @Autowired
    OrderService orderService;

    @PostMapping("/place")
    public ResponseEntity placeOrder(@RequestBody OrderRequestDto orderRequestDto )
            throws CustomerNotFoundException, ProductNotFoundException, InsufficientQuantityException,
            InvalidCardException {

        try{
            OrderResponseDto orderResponseDto =orderService.placeOrder(orderRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch(ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (InsufficientQuantityException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (InvalidCardException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }



    // get top 5 orders with highest order value

    // all the orders of a particular customer

    // top 5 orders of a customer based on cost

    // top 5 recently ordered orders of a customer
}
