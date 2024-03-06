package com.example.ECommerce.Model;

import com.example.ECommerce.Enum.Gender;
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
@Table(name="customer")
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "name")
    String name;

    @Column(name = "mob_no",unique = true,nullable = false)
    String mobNo;

    @Column(name = "email",unique = true,nullable = false)
    String emailId;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender")
    Gender gender;

    @OneToOne(mappedBy = "customer",cascade = CascadeType.ALL)
    Cart cart;

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<Card> cards=new ArrayList<>();

    @OneToMany(mappedBy = "customer",cascade = CascadeType.ALL)
    List<OrderEntity> orders=new ArrayList<>();
}
