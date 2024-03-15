package com.example.ECommerce.Dto.RequestDto;

import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@FieldDefaults(level = AccessLevel.PRIVATE)
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class SellerRequestDto {
    String name;
    String mobNo;
    String emailId;
}
