package com.easyShopping.easyShopping.repository;

import com.easyShopping.easyShopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail( @NotNull String email);
    Optional<User> findById(Long id);
}
