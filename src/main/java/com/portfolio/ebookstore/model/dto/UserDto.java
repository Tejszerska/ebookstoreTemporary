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
    private String username;
    private String name;
    private String surname;
    private String city;
    private String street;
    private String zipCode;
    private List<OrderDto> pastPurchases;
    private List<AuthorityDto> authorities;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
}

