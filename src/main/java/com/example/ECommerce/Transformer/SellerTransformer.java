package com.example.ECommerce.Transformer;

import com.example.ECommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Dto.ResponseDto.SellerUpdatedResponseDto;
import com.example.ECommerce.Model.Seller;
import lombok.experimental.UtilityClass;

@UtilityClass
public class SellerTransformer {
    public static Seller sellerRequestDtoToSeller(SellerRequestDto sellerRequestDto){
        return Seller.builder()
                .name(sellerRequestDto.getName())
                .mobNo(sellerRequestDto.getMobNo())
                .emailId(sellerRequestDto.getEmailId())
                .build();
    }

    public static SellerResponseDto sellerToSellerResponseDto(Seller seller){
        return SellerResponseDto.builder()
                .name(seller.getName())
                .message("Seller added successfully!!!")
                .build();
    }

    public static SellerUpdatedResponseDto sellerToSellerUpdatedResponseDto(Seller seller){
        return SellerUpdatedResponseDto.builder()
                .message("Seller Information Updated Successfully!!, Here you can see the updated seller details!!")
                .mobNo(seller.getMobNo())
                .name(seller.getName())
                .emailId(seller.getEmailId())
                .build();
    }
}
