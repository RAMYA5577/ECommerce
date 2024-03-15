package com.example.ECommerce.Service.ServiceImpl;

import com.example.ECommerce.Dto.RequestDto.ProductRequestDto;
import com.example.ECommerce.Dto.ResponseDto.ProductResponseDto;
import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Enum.ProductStatus;
import com.example.ECommerce.Exception.SellerNotFoundException;
import com.example.ECommerce.Model.Product;
import com.example.ECommerce.Model.Seller;
import com.example.ECommerce.Repository.ProductRepository;
import com.example.ECommerce.Repository.SellerRepository;
import com.example.ECommerce.Service.ProductService;
import com.example.ECommerce.Service.SellerService;
import com.example.ECommerce.Transformer.ProductTransformer;
import org.hibernate.sql.ast.tree.expression.Collation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Collections.*;

@Service
public class ProductServiceImpl implements ProductService {
    @Autowired
    SellerRepository sellerRepository;
    @Autowired
    ProductRepository productRepository;



    @Override
    public ProductResponseDto addProduct(ProductRequestDto productRequestDto) throws SellerNotFoundException {
        Seller seller=sellerRepository.findByEmailId(productRequestDto.getSellerEmailId());
        if(seller==null){
            throw new SellerNotFoundException("Seller not found with provided emailId!!");
        }

        //dto to entity
        Product product= ProductTransformer.productRequestDtoToProduct(productRequestDto);
        product.setSeller(seller);
        seller.getProducts().add(product);

        //save
        Seller savedSeller=sellerRepository.save(seller);    //saves both seller and product
        Product savedProduct=savedSeller.getProducts().get(savedSeller.getProducts().size()-1);
        //entity to dto
        ProductResponseDto productResponseDto=ProductTransformer.productToProductResponseDto(savedProduct);
        return productResponseDto;
    }

    @Override
    public List<ProductResponseDto> getAllProductsByCategoryAndPrice(Category category, int price) {
        List<Product> products=productRepository.findByCategoryAndPrice(category,price);
        List<ProductResponseDto> productResponseDtos=new ArrayList<>();
        for(Product product:products){
            productResponseDtos.add(ProductTransformer.productToProductResponseDto(product));
        }
        return productResponseDtos;
    }

    @Override
    public List<String> getAllProductsOfACategory(Category category) {
    List<String> products=new ArrayList<>();
    List<Product> productList=productRepository.findByCategory(category);
    for(Product product:productList){
        products.add(product.getName());
    }
        return products;
    }

    @Override
    public List<String> getAllProductsOfACategoryAndPriceGreaterThan500(Category category) {
        List<String> products=new ArrayList<>();
        List<Product> productList=productRepository.findByCategory(category);
        for(Product product:productList){
            if(product.getPrice()>500){
                products.add(product.getName());
            }
        }
        return products;
    }

    @Override
    public List<String> getTop5CheapestProducts(Category category) {
        List<Product> products=productRepository.findByCategory(category);
        Map<Integer,Integer> map=new HashMap<>(); //id,price
        List<Integer> prices=new ArrayList<>();  //price
        for(Product product:products){
            map.put(product.getId(),product.getPrice());
        }
        for(int price:map.keySet()){
            prices.add(price);
        }
        Collections.sort(prices);
        List<Product> productList=new ArrayList<>();
        int count=0;
        for(int k=0;k<prices.size();k++){

            for(int e:map.keySet()){
                if(map.get(e)<=prices.get(k)){
                    productList.add(productRepository.findById(e).get());
                    count++;

                }

                if (count==5) break;
            }
        }
        List<String> top5CheapestProducts=new ArrayList<>();
        for (Product product:productList){
            top5CheapestProducts.add(product.getName());
        }
        return top5CheapestProducts;
    }

    @Override
    public List<String> getTop5CostliestProducts(Category category) {
        List<Product> products=productRepository.findAll();
        Map<Integer,Integer> map=new HashMap<>();
        List<Integer> prices=new ArrayList<>();
        for(Product product:products){
            map.put(product.getId(),product.getPrice());
        }
        for(int price:map.keySet()){
            prices.add(price);
        }
        Collections.sort(prices);
        List<Product> productList=new ArrayList<>();
        for(int k=prices.size()-1;k>0;k--){
            int count=0;
            for(int e:map.keySet()){
                if(map.get(e)==prices.get(e)){
                    productList.add(productRepository.findById(e).get());
                    count++;
                }
                if (count==5) break;
            }
        }
        List<String> top5CostliestProducts=new ArrayList<>();
        for (Product product:productList){
            top5CostliestProducts.add(product.getName());
        }
        return top5CostliestProducts;
    }

    @Override
    public List<String> getAllProductsBasedOnSellerEmailId(String emailId) throws SellerNotFoundException {
        Seller seller=sellerRepository.findByEmailId(emailId);
        if(seller==null){
            throw new SellerNotFoundException("Invalid emailId!!");
        }

        List<String> products=new ArrayList<>();
        for (Product product:seller.getProducts()){
            products.add(product.getName());
        }
        return products;
    }

    @Override
    public List<String> getAllOutOfStockOfAParticularCategory(Category category) {
        List<Product> products=productRepository.findByCategory(category);
        List<String> outOfStockProducts=new ArrayList<>();
        for (Product product:products){
            if (product.getProductStatus().equals(ProductStatus.OUT_OF_STOCK)){
                outOfStockProducts.add(product.getName());
            }
        }
        return outOfStockProducts;
    }

    @Override
    public List<String> getAllAvailableStockOfAParticularCategory(Category category) {
        List<Product> products=productRepository.findByCategory(category);
        List<String> availableProducts=new ArrayList<>();
        for (Product product:products){
            if (product.getProductStatus().equals(ProductStatus.IN_STOCK)){
                availableProducts.add(product.getName());
            }
        }
        return availableProducts;
    }


}
