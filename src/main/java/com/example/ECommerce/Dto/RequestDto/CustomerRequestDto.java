package com.example.ECommerce.Dto.RequestDto;

import com.example.ECommerce.Enum.Gender;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class CustomerRequestDto {
    String name;
    int age;
    String mobNo;
    String emailId;
    Gender gender;

}
