package com.easyShopping.easyShopping.repository;

import com.easyShopping.easyShopping.model.ShoppingList;
import com.easyShopping.easyShopping.model.User;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class ShoppingListCustomizedRepositoryImpl implements ShoppingListCustomizedRepository {
    @PersistenceContext
    private EntityManager entityManager;
    private UserRepository userRepository;

    public List<ShoppingList> findByUserId(Long userId)
    {
        User user = new User();
        //user =  userRepository.findById(userId);
        return null;
    }
}
