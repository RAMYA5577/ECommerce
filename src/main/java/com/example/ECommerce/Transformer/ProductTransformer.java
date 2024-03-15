package com.example.ECommerce.Transformer;

import com.example.ECommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Enum.ProductStatus;
import com.example.ECommerce.Model.Product;
import lombok.experimental.UtilityClass;

@UtilityClass
public class ProductTransformer {
    public static Product productRequestDtoToProduct(ProductRequestDto productRequestDto){
        return Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .productStatus(ProductStatus.IN_STOCK)
                .category(productRequestDto.getCategory())
                .quantity(productRequestDto.getQuantity())
                .build();
    }

    public static ProductResponseDto productToProductResponseDto(Product product){
        return ProductResponseDto.builder()
                .message("Product Added Successfully!!")
                .productName(product.getName())
                .sellerName(product.getSeller().getName())
                .category(product.getCategory())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .productStatus(product.getProductStatus())
                .build();
    }
}
