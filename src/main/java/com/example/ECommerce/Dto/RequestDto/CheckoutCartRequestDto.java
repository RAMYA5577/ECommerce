package com.example.ECommerce.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CheckoutCartRequestDto {
    String emailId;

    String cardNo;

    int cvv;
}
