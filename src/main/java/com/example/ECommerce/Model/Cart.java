package com.example.ECommerce.Model;

import jakarta.persistence.*;
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
@Table(name="cart")
public class Cart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "cart_total")
    int cartTotal;

    @OneToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
    List<Item> items=new ArrayList<>();
}
