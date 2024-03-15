package com.example.ECommerce.Dto.ResponseDto;

import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Enum.ProductStatus;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@AllArgsConstructor
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@Builder
public class ProductResponseDto {
    String message;
    String sellerName;
    String productName;
    int quantity;
    Category category;
    int price;
    ProductStatus productStatus;

}
