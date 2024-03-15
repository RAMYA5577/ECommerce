package com.example.ECommerce.Repository;

import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product,Integer> {
    List<Product> findByCategoryAndPrice(Category category, int price);
    List<Product> findByCategory(Category category);

}
