package com.example.ECommerce.Transformer;

import com.example.ECommerce.Dto.RequestDto.CardRequestDto;
import com.example.ECommerce.Dto.ResponseDto.CardResponseDto;
import com.example.ECommerce.Model.Card;
import lombok.experimental.UtilityClass;

@UtilityClass
public class CardTransformer {
    public static Card cardRequestDtoToCard(CardRequestDto cardRequestDto){
        return Card.builder()
                .cardNo(cardRequestDto.getCardNo())
                .cardType(cardRequestDto.getCardType())
                .validTill(cardRequestDto.getValidTill())
                .cvv(cardRequestDto.getCvv())
                .build();
    }


    public static CardResponseDto cardToCardResponseDto(Card card){
        return CardResponseDto.builder()
                .cardNo(card.getCardNo())
                .customerName(card.getCustomer().getName())
                .cardType(card.getCardType())
                .build();
    }
}
