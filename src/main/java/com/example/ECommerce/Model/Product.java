package com.example.ECommerce.Model;

import com.example.ECommerce.Enum.Category;
import com.example.ECommerce.Enum.ProductStatus;
import jakarta.persistence.*;
import jdk.jshell.Snippet;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "price")
    int price;

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    Category category;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    ProductStatus productStatus;

    @ManyToOne
    @JoinColumn
    Seller seller;

    @OneToMany(mappedBy = "product",cascade = CascadeType.ALL)
    List<Item> items=new ArrayList<>();
}
