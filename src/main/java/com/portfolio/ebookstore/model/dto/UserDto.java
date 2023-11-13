package com.portfolio.ebookstore.model.dto;

import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.util.List;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String zipCode;
    private String role;
    private List<OrderDto> pastPurchases;
}

