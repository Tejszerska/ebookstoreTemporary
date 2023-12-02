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
    @Singular
    @ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
    @JoinTable(name = "users_authorities", joinColumns = {
            @JoinColumn(name = "USERS_ID", referencedColumnName = "ID") }, inverseJoinColumns = {
            @JoinColumn(name = "AUTHORITIES_ID", referencedColumnName = "ID") })
    private List<Authority> authorities;
    @Builder.Default
    private Boolean accountNonExpired = true;
    @Builder.Default
    private Boolean accountNonLocked = true;
    @Builder.Default
    private Boolean credentialsNonExpired = true;
    @Builder.Default
    private Boolean enabled = true;

    public User(String email, String password, Address address) {
        this.email = email;
        this.password = password;
        this.address = address;
    }
}
