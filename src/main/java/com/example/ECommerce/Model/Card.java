package com.example.ECommerce.Model;

import com.example.ECommerce.Enum.CardType;
import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;
import org.hibernate.annotations.CreationTimestamp;

import java.sql.Date;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
@Table(name="card")
public class Card{
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        int id;

        @Column(name = "card_no",unique = true,nullable = false)
        String cardNo;

        @Column(name = "card_type")
        @Enumerated(EnumType.STRING)
        CardType cardType;

        @CreationTimestamp
        Date validTill;

        @Column(name ="cvv")
        int cvv;

        @ManyToOne
        @JoinColumn
        Customer customer;
        }
