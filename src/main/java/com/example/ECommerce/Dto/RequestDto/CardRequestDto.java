package com.example.ECommerce.Dto.RequestDto;

import com.example.ECommerce.Enum.CardType;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.sql.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CardRequestDto {
    String customerMailId;
    String cardNo;
    CardType cardType;
    Date validTill;
    int cvv;
}

