package com.example.ECommerce.Controller;

import com.example.ECommerce.Dto.RequestDto.CardRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ECommerce.Exception.CustomerNotFoundException;
import com.example.ECommerce.Service.CardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("card")
public class CardController {
    @Autowired
    CardService cardService;

    @PostMapping("/add")
    public ResponseEntity addCard(@RequestBody CardRequestDto cardRequestDto) throws CustomerNotFoundException {
        try{
            CardResponseDto cardResponseDto=cardService.addcard(cardRequestDto);
            return new ResponseEntity<>(cardResponseDto, HttpStatus.CREATED);
        }
        catch(CustomerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    // tell me the card type which exists max number of times.

    // tell me the card type which exists min number of times.
}
