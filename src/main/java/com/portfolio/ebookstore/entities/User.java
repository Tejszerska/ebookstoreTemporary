package com.portfolio.ebookstore.entities;

import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.enums.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String email;
    private String password;
    @Embedded
    private Address address;
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany (mappedBy = "user")
    private List<Order> pastPurchases;
}
