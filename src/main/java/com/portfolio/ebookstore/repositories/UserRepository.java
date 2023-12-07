package com.portfolio.ebookstore.repositories;

import com.portfolio.ebookstore.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String email);

    @Query("SELECT u FROM User u WHERE u.username = ?#{ principal.username}")
    Optional<User> findLoginUser();
}
