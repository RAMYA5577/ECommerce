package com.example.ECommerce.Controller;

import com.example.ECommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Model.Product;
import com.example.ECommerce.Service.ProductService;
import com.example.ECommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("product")
public class ProductController {
    @Autowired
    ProductService productService;


    //1
    //add product
    @PostMapping("/add")
    public ResponseEntity addProduct(@RequestBody ProductRequestDto productRequestDto)
            throws SellerNotFoundException {
        try {
            ProductResponseDto productResponseDto = productService.addProduct(productRequestDto);
            return new ResponseEntity<>(productResponseDto, HttpStatus.CREATED);
        } catch (SellerNotFoundException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }

    }

    //2
    //get product basedOn price and category
    @GetMapping("/get/category/{category}/price/{price}")
    public ResponseEntity getAllProductsByCategoryAndPrice(@PathVariable("category") Category category, @PathVariable("price") int price) {
        List<ProductResponseDto> productResponseDtos = productService.getAllProductsByCategoryAndPrice(category, price);
        return new ResponseEntity<>(productResponseDtos, HttpStatus.FOUND);

    }

    //3
// get all the products of a category
    @GetMapping("/getAllTheProductsOfACategory")
    public ResponseEntity getAllProductsOfACategory(@RequestParam("category") Category category){
        List<String> products=productService.getAllProductsOfACategory(category);
        return new ResponseEntity<>(products,HttpStatus.FOUND);
    }
//4
// get all the products in a category who have price greater than 500
    @GetMapping("/getProductsOfSameCategoryAndPriceGreaterThan500")
    public ResponseEntity getAllProductsOfACategoryAndPriceGreaterThan500(@RequestParam("category") Category category){
        List<String> products=productService.getAllProductsOfACategoryAndPriceGreaterThan500(category);
        return new ResponseEntity<>(products,HttpStatus.FOUND);
    }


//5
// get the top 5 cheapest products in a category
@GetMapping("/getTop5CheapestProducts")
    public ResponseEntity getTop5CheapestProducts(@RequestParam("category") Category category){
        List<String> top5CheapestProducts=productService.getTop5CheapestProducts(category);
        return new ResponseEntity<>(top5CheapestProducts,HttpStatus.FOUND);
}
//6
// get top 5 costliest products in a category
@GetMapping("/getTop5CostliestProducts")
public ResponseEntity getTop5CostliestProducts(@RequestParam("category") Category category){
    List<String> top5CostliestProducts=productService.getTop5CostliestProducts(category);
    return new ResponseEntity<>(top5CostliestProducts,HttpStatus.FOUND);
}
//7
// get all the products of seller based on emailid
@GetMapping("/getAllProductsBasedOnSeller")
    public ResponseEntity getAllProductsBasedOnSellerEmailId(@RequestParam("email") String emailId) throws SellerNotFoundException{
        try{
            List<String> productsOfAParticularSeller=productService.getAllProductsBasedOnSellerEmailId(emailId);
            return new ResponseEntity<>(productsOfAParticularSeller,HttpStatus.FOUND);
        }
        catch(SellerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
}
//8
// get all the out of stock products for a particular catgeory
    @GetMapping("/getOutOfStockOfACategory")
    public ResponseEntity getAllOutOfStockOfAParticularCategory(@RequestParam("category") Category category){
        List<String> outOfStockProducts=productService.getAllOutOfStockOfAParticularCategory(category);
        return new ResponseEntity<>(outOfStockProducts,HttpStatus.FOUND);
    }

    //9
    //get all available stock products for a particular category
    @GetMapping("/getAvailableStockOfACategory")
    public ResponseEntity getAllAvailableStockOfAParticularCategory(@RequestParam("category") Category category){
        List<String> availableStockProducts=productService.getAllAvailableStockOfAParticularCategory(category);
        return new ResponseEntity<>(availableStockProducts,HttpStatus.FOUND);
    }

}