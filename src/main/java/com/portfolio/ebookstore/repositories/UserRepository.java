package com.portfolio.ebookstore.repositories;

import com.portfolio.ebookstore.entities.Ebook;
import com.portfolio.ebookstore.entities.User;
import jakarta.validation.constraints.Email;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository  extends JpaRepository<User, Long> {
    User findByEmail(@Email String email);
}
