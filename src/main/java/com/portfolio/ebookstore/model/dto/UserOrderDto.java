package com.portfolio.ebookstore.model.dto;

import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.Address;
import com.portfolio.ebookstore.model.enums.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserOrderDto {
    private Long id;
    private String email;
    private String password;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String zipCode;
    private String role;
}
