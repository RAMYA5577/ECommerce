package com.example.ECommerce.Service;

import com.example.ECommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Dto.ResponseDto.SellerUpdatedResponseDto;
import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Exception.EmptyListException;
import com.example.ECommerce.Exception.InvalidInputException;
import com.example.ECommerce.Exception.SellerNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;


public interface SellerService {
    SellerResponseDto addSeller(SellerRequestDto sellerRequestDto);

    SellerUpdatedResponseDto updateSellerInfoWithEmailId(String name, String mobNo, String eMailId) throws SellerNotFoundException;

    List<String> getSellersWithParticularcategory(Category category)throws InvalidInputException, EmptyListException;

    List<String> getAllProductsOfASellerWithParticularCategory(String emailId, Category category) throws InvalidInputException,SellerNotFoundException,EmptyListException;

    List<String> getSellerWithMaxProducts();

    List<String> getSellerWithMinProducts();

    List<String> getSellerWithCostliestProduct();

    List<String> getSellerWithCheapestProduct();
}
