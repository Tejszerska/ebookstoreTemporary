package com.portfolio.ebookstore.model;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Address {
    private String name;
    private String surname;
    private String city;
    private String street;
    private String zipCode;


    @Override
    public String toString() {
        return  "First name: " + name +
                " , surname: " + surname +
                " , city: " + city +
                " , street: " + street +
                " , zipCode: " + zipCode ;
    }
}
