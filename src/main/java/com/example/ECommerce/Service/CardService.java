package com.example.ECommerce.Service;

import com.example.ECommerce.Dto.RequestDto.CardRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ECommerce.Exception.CustomerNotFoundException;

public interface CardService {
    CardResponseDto addcard(CardRequestDto cardRequestDto) throws CustomerNotFoundException;
}
