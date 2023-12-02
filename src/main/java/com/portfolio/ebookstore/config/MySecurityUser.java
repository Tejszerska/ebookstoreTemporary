package com.portfolio.ebookstore.config;

import com.portfolio.ebookstore.entities.Order;
import com.portfolio.ebookstore.model.Address;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.Email;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Set;
@Getter
@Setter
public class MySecurityUser extends User {
    private static final long serialVersionUID = 1L;
    private Address address;
    private List<Order> pastPurchases;
    public MySecurityUser(String username, String password, boolean enabled, boolean accountNonExpired,
                          boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities,
                         Address address, List<Order> pastPurchases) {
        super(username, password, enabled, accountNonExpired,credentialsNonExpired,accountNonLocked, authorities);
        this.address = address;
        this.pastPurchases = pastPurchases;
    }
}
