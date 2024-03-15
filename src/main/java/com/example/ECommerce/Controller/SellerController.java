package com.example.ECommerce.Controller;

import com.example.ECommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Dto.ResponseDto.SellerUpdatedResponseDto;
import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Exception.EmptyListException;
import com.example.ECommerce.Exception.InvalidInputException;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Service.SellerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("seller")
public class SellerController {
    @Autowired
    SellerService sellerService;

    //1
    //add seller
    @PostMapping("/add")
    public ResponseEntity addSeller(@RequestBody SellerRequestDto sellerRequestDto){
        SellerResponseDto sellerResponseDto=sellerService.addSeller(sellerRequestDto);
        return new ResponseEntity<>(sellerResponseDto, HttpStatus.CREATED);
    }

    //2
    // update the seller info based on email.
    @PutMapping("/update_seller")
    public ResponseEntity updateSellerInfoWithEmailId(@RequestParam("name") String name,@RequestParam("mobNo") String mobNo,@RequestParam("eMailId") String eMailId) throws SellerNotFoundException {
        try{
            SellerUpdatedResponseDto sellerUpdatedResponseDto=sellerService.updateSellerInfoWithEmailId(name,mobNo,eMailId);
            return new ResponseEntity<>(sellerUpdatedResponseDto,HttpStatus.ACCEPTED);
        }
        catch (SellerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }

    }//3
    // get all the sellers who sell products of a particular category
    @GetMapping("/getSellersWithParticularCategory")
    public ResponseEntity getSellersWithParticularCategory(@RequestParam("/category")Category category)throws
            InvalidInputException, EmptyListException {
        try{
            List<String> sellersOfAParticularCategory=sellerService.getSellersWithParticularcategory(category);
            return new ResponseEntity<>(sellersOfAParticularCategory,HttpStatus.FOUND);
        }
        catch(InvalidInputException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(EmptyListException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
        }
    }

    //4
    // get all the products sold by a seller in a category
    @GetMapping("/getAllProductsOfASellerWithParticularCategory")
    public ResponseEntity getAllProductsOfASellerWithParticularCategory(@RequestParam("emailId") String emailId, @RequestParam("category") Category category)
            throws SellerNotFoundException,InvalidInputException,EmptyListException{
        try{
            List<String> products=sellerService.getAllProductsOfASellerWithParticularCategory(emailId,category);
            return new ResponseEntity<>(products,HttpStatus.FOUND);
        }
        catch(EmptyListException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.FOUND);
        }
        catch(SellerNotFoundException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.NOT_FOUND);
        }
        catch(InvalidInputException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }
    }

    //5
    // seller with highest number of products
    @GetMapping("/sellerWithMaxProducts")
    public ResponseEntity getSellerWithMaxProducts(){
      List<String> sellers=sellerService.getSellerWithMaxProducts();
      return new ResponseEntity<>(sellers,HttpStatus.FOUND);
    }

    //6
    // seller with minimum number of products
    @GetMapping("/sellerWithMinProducts")
    public ResponseEntity getSellerWithMinProducts(){
        List<String> sellers=sellerService.getSellerWithMinProducts();
        return new ResponseEntity<>(sellers,HttpStatus.FOUND);
    }

    //7
    // seller(s) selling the costliest product
    @GetMapping("/sellersWithCostliestProduct")
    public ResponseEntity getSellersWithCostliestProduct(){
        List<String> sellers=sellerService.getSellerWithCostliestProduct();
        return new ResponseEntity<>(sellers,HttpStatus.FOUND);
    }

    //8
    // seller(s) selling the cheapest product
    @GetMapping("/sellersWithCheapestProduct")
    public ResponseEntity getSellersWithCheapestProduct(){
        List<String> sellers=sellerService.getSellerWithCheapestProduct();
        return new ResponseEntity<>(sellers,HttpStatus.FOUND);
    }
}
