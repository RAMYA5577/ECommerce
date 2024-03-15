package com.example.ECommerce.Controller;

import com.example.ECommerce.Dto.RequestDto.CheckoutCartRequestDto;
import com.example.ECommerce.Dto.RequestDto.ItemRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CartResponseDto;
import com.example.ECommerce.Dto.ResponseDto.OrderResponseDto;
import com.example.ECommerce.Exception.*;
import com.example.ECommerce.Model.Item;
import com.example.ECommerce.Repository.OrderRepository;
import com.example.ECommerce.Service.CartService;
import com.example.ECommerce.Service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("cart")
public class CartController {


    @Autowired
    ItemService itemService;

    @Autowired
    CartService cartService;

    @Autowired
    private OrderRepository orderRepository;
    @PostMapping("/add")
    public ResponseEntity addToCart(@RequestBody ItemRequestDto itemRequestDto)
            throws ProductNotFoundException, CustomerNotFoundException, InsufficientQuantityException, OutOfStockException {
        try{
            Item item = itemService.createItem(itemRequestDto);
            CartResponseDto cartResponseDto = cartService.addToCart(item,itemRequestDto);
            return new ResponseEntity(cartResponseDto,HttpStatus.ACCEPTED);
        }
        catch (ProductNotFoundException e){
            return new ResponseEntity(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (InsufficientQuantityException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (OutOfStockException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/checkout")   // correct the bug
    public ResponseEntity checkOutCart(@RequestBody CheckoutCartRequestDto checkoutCartRequestDto)
            throws CustomerNotFoundException, InvalidCardException, EmptyCartException,
            InsufficientQuantityException
    {

        try{
            OrderResponseDto orderResponseDto = cartService.checkOutCart(checkoutCartRequestDto);
            return new ResponseEntity(orderResponseDto,HttpStatus.CREATED);
        }
        catch (CustomerNotFoundException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

        catch (InvalidCardException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (EmptyCartException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
        catch (InsufficientQuantityException e){
            return new ResponseEntity(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }

    // add the functionality of email sending in direct order and checkout cart
    // kunaljindal995@gmail.com

    // integrate swagger
}
