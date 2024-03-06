package com.example.ECommerce.Model;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="order_info")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;

    @Column(name = "order_no")
    String orderNo;

    @Column(name = "quantity")
    int quantity;

    @Column(name = "total_value")
    int totalValue;

    @Column(name = "card_used")
    String cardUsed;

    @CreationTimestamp
    @Column(name = "date_of_order")
    Date dateOfOrder;

    @ManyToOne
    @JoinColumn
    Customer customer;

    @OneToMany(mappedBy = "orderEntity",cascade = CascadeType.ALL)
    List<Item> items = new ArrayList<>();
}
