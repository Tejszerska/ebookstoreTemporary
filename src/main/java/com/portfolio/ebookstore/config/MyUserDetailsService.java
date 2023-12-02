package com.portfolio.ebookstore.config;

import com.portfolio.ebookstore.entities.User;
import com.portfolio.ebookstore.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RequiredArgsConstructor
@Slf4j
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {

        Optional<User> userByUsername = userRepository.findByEmail(email);
        if (!userByUsername.isPresent()) {
            log.error("Could not find user with that email: {}", email);
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        User user = userByUsername.get();
        if (user == null || !user.getUsername().equals(email)) {
            log.error("Could not find user with that email: {}", email);
            throw new UsernameNotFoundException("Invalid credentials!");
        }
        Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
        user.getAuthorities().stream().forEach(authority ->
                grantedAuthorities.add(new SimpleGrantedAuthority(authority.getAuthority())));

        return new MySecurityUser(user.getUsername(), user.getPassword(), true, true, true, true, grantedAuthorities,
                user.getAddress(), user.getPastPurchases());
    }
}
