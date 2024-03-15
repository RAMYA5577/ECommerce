package com.example.ECommerce.Dto.RequestDto;

import com.example.ECommerce.Enum.Category;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductRequestDto {
    String name;
    int price;
    Category category;
    int quantity;
    String sellerEmailId;
}
