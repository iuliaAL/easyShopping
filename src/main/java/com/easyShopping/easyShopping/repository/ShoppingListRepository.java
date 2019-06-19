package com.easyShopping.easyShopping.repository;

import com.easyShopping.easyShopping.model.ShoppingList;
import com.easyShopping.easyShopping.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ShoppingListRepository extends JpaRepository<ShoppingList,Long>,ShoppingListCustomizedRepository {

    List<ShoppingList> findByUserId(Long userId);

}
