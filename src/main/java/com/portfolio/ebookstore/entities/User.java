package com.portfolio.ebookstore.entities;

import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
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
    @NotBlank
    private String password;
    @Embedded
    private Address address;
    @NotNull
    @Enumerated(EnumType.STRING)
    private Role role;
    @OneToMany (mappedBy = "user")
    private List<Order> pastPurchases;
}
