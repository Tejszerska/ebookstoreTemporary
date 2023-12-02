package com.portfolio.ebookstore.model.dto;

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

