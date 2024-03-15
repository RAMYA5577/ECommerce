package com.example.ECommerce.Dto.ResponseDto;

import com.example.ECommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerResponseDto {
    String message;
    String name;
    int age;
    String emailId;
    String mobNo;
    Gender gender;
}
