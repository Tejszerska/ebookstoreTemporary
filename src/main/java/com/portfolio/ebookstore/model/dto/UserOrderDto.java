package com.portfolio.ebookstore.model.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
