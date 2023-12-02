package com.portfolio.ebookstore.entities;

import com.portfolio.ebookstore.model.Address;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import lombok.*;

import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Email
    private String email;
    private String password;
    @Embedded
    private Address address;
    @OneToMany (mappedBy = "user")
    private List<Order> pastPurchases;

    public User(String email, String password, Address address) {
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
