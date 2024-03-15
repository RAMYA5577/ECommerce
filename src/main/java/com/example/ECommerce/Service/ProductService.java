package com.example.ECommerce.Service;

import com.example.ECommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Exception.SellerNotFoundException;

import java.util.List;

public interface ProductService {

     ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException;

     List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price);

     List<String> getAllProductsOfACategory(Category category);

     List<String> getAllProductsOfACategoryAndPriceGreaterThan500(Category category);

     List<String> getTop5CheapestProducts(Category category);

     List<String> getTop5CostliestProducts(Category category);

     List<String> getAllProductsBasedOnSellerEmailId(String emailId) throws SellerNotFoundException;

     List<String> getAllOutOfStockOfAParticularCategory(Category category);
     List<String> getAllAvailableStockOfAParticularCategory(Category category);


}
