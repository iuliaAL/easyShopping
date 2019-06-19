package com.easyShopping.easyShopping.repository;

import com.easyShopping.easyShopping.model.SupermarketProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface SupermarketProductRepository extends JpaRepository<SupermarketProduct,Long> {
    @Transactional
    @Modifying
    @Query("DELETE FROM SupermarketProduct e where id = ?1")
    void deleteById(Long id);
}
