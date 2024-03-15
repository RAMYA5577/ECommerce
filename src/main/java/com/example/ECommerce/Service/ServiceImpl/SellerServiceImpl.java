package com.example.ECommerce.Service.ServiceImpl;

import com.example.ECommerce.Dto.RequestDto.SellerRequestDto;
import com.example.ECommerce.Dto.ResponseDto.SellerResponseDto;
import com.example.ECommerce.Dto.ResponseDto.SellerUpdatedResponseDto;
import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Exception.EmptyListException;
import com.example.ECommerce.Exception.InvalidInputException;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Model.Product;
import com.example.ECommerce.Model.Seller;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Repository.SellerRepository;
import com.example.ECommerce.Service.SellerService;
import com.example.ECommerce.Transformer.SellerTransformer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SellerServiceImpl implements SellerService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;
    @Override
    public SellerResponseDto addSeller(SellerRequestDto sellerRequestDto) {
        //dto to entity
        Seller seller = SellerTransformer.sellerRequestDtoToSeller(sellerRequestDto);
        //save
        Seller savedSeller= sellerRepository.save(seller);
        //entity to dto
        SellerResponseDto sellerResponseDto=SellerTransformer.sellerToSellerResponseDto(savedSeller);
        return sellerResponseDto;
    }

    @Override
    public SellerUpdatedResponseDto updateSellerInfoWithEmailId(String name, String mobNo, String eMailId)
            throws SellerNotFoundException {
        Seller seller=sellerRepository.findByEmailId(eMailId);
        if(seller==null){
            throw new SellerNotFoundException("Invalid EmailId");
        }
        seller.setName(name);
        seller.setMobNo(mobNo);
        Seller savedSeller=sellerRepository.save(seller);
        //dto
        SellerUpdatedResponseDto sellerUpdatedResponseDto=SellerTransformer.sellerToSellerUpdatedResponseDto(savedSeller);
        return sellerUpdatedResponseDto;
    }

    @Override
    public List<String> getSellersWithParticularcategory(Category category)
            throws InvalidInputException, EmptyListException {
        boolean flag=false;
        for(Category category1 : Category.values()){
            if(category1.equals(category)){
                flag=true;
            }
        }
        if(flag==false){
            throw new InvalidInputException("Invalid Category Provided!!");
        }
        List<String> sellersOfParticularCategory=new ArrayList<>();
        List<Product> products=productRepository.findByCategory(category);
        for(Product product: products){
            sellersOfParticularCategory.add(product.getSeller().getName());
        }
        if(sellersOfParticularCategory.size()==0){
            throw new EmptyListException("List is empty!!, No seller is present!!");
        }
        return sellersOfParticularCategory;
    }

    @Override
    public List<String> getAllProductsOfASellerWithParticularCategory(String emailId, Category category) throws SellerNotFoundException,InvalidInputException,EmptyListException {
        List<String> products=new ArrayList<>();
        Seller seller=sellerRepository.findByEmailId(emailId);
        if(seller==null){
            throw new SellerNotFoundException("No Seller is present with provided EmailId!!");
        }
        boolean flag=false;
        for(Category category1: Category.values()){
            if(category1.equals(category)){
                flag=true;
            }
        }
        if(flag==false){
            throw new InvalidInputException("Invalid type of Category Provided!!");
        }
        for(Product product: seller.getProducts()){
            if(product.getCategory().equals(category)){
                products.add(product.getName());
            }
        }
        return products;
    }

    @Override
    public List<String> getSellerWithMaxProducts() {
        List<String> sellers=new ArrayList<>();
        List<Seller> sellerList=sellerRepository.findAll();
        int max=0;
        for(Seller seller:sellerList){
            if(seller.getProducts().size()>max){
                max=seller.getProducts().size();
            }
        }
        for(Seller seller:sellerList){
            if(seller.getProducts().size()==max)
            sellers.add(seller.getName());
        }
        return sellers;
    }

    @Override
    public List<String> getSellerWithMinProducts() {
        List<String> sellers=new ArrayList<>();
        List<Seller> sellerList=sellerRepository.findAll();
        int min=Integer.MAX_VALUE;
        for(Seller seller:sellerList){
            if(seller.getProducts().size()<min){
                min=seller.getProducts().size();
            }
        }
        for(Seller seller:sellerList){
            if(seller.getProducts().size()==min)
                sellers.add(seller.getName());
        }
        return sellers;
    }

    @Override
    public List<String> getSellerWithCostliestProduct() {
        List<String> sellers=new ArrayList<>();
        List<Product> productList=productRepository.findAll();
        int max=0;
        for(Product product:productList){
            if(product.getPrice()>max){
                max=product.getPrice();
            }
        }
        for(Product product:productList){
            if(product.getPrice()==max){
                sellers.add(product.getSeller().getName());
            }
        }
        return sellers;
    }

    @Override
    public List<String> getSellerWithCheapestProduct() {
        List<String> sellers=new ArrayList<>();
        List<Product> productList=productRepository.findAll();
        int min=Integer.MAX_VALUE;
        for(Product product:productList){
            if(product.getPrice()<min){
                min= product.getPrice();
            }
        }
        for(Product product:productList){
            if(product.getPrice()==min)
                sellers.add(product.getSeller().getName());
        }

        return sellers;
    }


}
