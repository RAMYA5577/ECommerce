package com.example.ECommerce.Dto.ResponseDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class SellerUpdatedResponseDto {
    String message;
    String name;
    String mobNo;
    String emailId;
}
