package com.easyShopping.easyShopping.repository;

import com.easyShopping.easyShopping.model.ListProduct;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ListProductRepository extends JpaRepository<ListProduct,Long> {
}
