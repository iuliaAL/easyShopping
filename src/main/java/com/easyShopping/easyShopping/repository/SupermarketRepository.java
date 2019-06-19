package com.easyShopping.easyShopping.repository;

import com.easyShopping.easyShopping.model.Supermarket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.validation.constraints.NotNull;
import java.util.Optional;

@Repository
public interface SupermarketRepository extends JpaRepository<Supermarket,Long> {
    Optional<Supermarket> findByName(@NotNull String name);
    @Transactional
    @Modifying
    @Query("DELETE FROM Supermarket e WHERE id = ?1")
    void deleteById(Long id);
}
