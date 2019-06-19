package com.easyShopping.easyShopping.repository;

import com.easyShopping.easyShopping.model.ShoppingList;
import com.easyShopping.easyShopping.model.User;

import java.util.List;

public interface ShoppingListCustomizedRepository {

   List<ShoppingList> findByUserId(Long userId);
}
