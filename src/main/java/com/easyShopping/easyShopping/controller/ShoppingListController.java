package com.easyShopping.easyShopping.controller;

import com.easyShopping.easyShopping.dto.ListProductDto;
import com.easyShopping.easyShopping.dto.ProductDto;
import com.easyShopping.easyShopping.dto.ShoppingListDto;
import com.easyShopping.easyShopping.dto.UserDto;
import com.easyShopping.easyShopping.model.User;
import com.easyShopping.easyShopping.service.ShoppingListService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ShoppingListController {

    @Autowired
    private ShoppingListService shoppingListService;

    @PostMapping("/shoppingLists")
    public ResponseEntity<ShoppingListDto> createShopppingList(@RequestBody ShoppingListDto shoppingListDto) {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ShoppingListDto shoppingListDtoResult = shoppingListService.createShoppingList(user.getId(),shoppingListDto);
        return ResponseEntity.ok(shoppingListDtoResult);
    }
    @PostMapping("/shoppingLists/users/{userId}/{shoppingListId}")
    public ResponseEntity<ShoppingListDto> addNewUserToShoppingList(@PathVariable final Long shoppingListId, @PathVariable final Long userId)
    {
        ShoppingListDto shoppingListDtoResult = shoppingListService.addNewUserToShoppingList(userId,shoppingListId);
        return ResponseEntity.ok(shoppingListDtoResult);
    }
    @PostMapping("/shoppingLists/products/{shoppingListId}")
    public ResponseEntity<ShoppingListDto>addProductToShoppingList(@RequestBody ListProductDto listProductDto, @PathVariable final Long shoppingListId)
    {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        ShoppingListDto shoppingListDtoResult=shoppingListService.addProductToList(user.getId(),shoppingListId,listProductDto);
        return ResponseEntity.ok(shoppingListDtoResult);
    }

    @GetMapping("/shoppingLists")
    public List<ShoppingListDto> findByUserId() {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return shoppingListService.findByUserId(user.getId());
    }

    @GetMapping("/shoppingLists/products/{shoppingListId}")
    public List<ProductDto> getProductsForShoppingList(@PathVariable final Long shoppingListId)
    {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  shoppingListService.showProductsFromAList(shoppingListId,user.getId());
    }

    @PutMapping("/shoppingLists/{id}/{name}")
    public ResponseEntity<ShoppingListDto> updateShoppingList (@PathVariable Long id, @PathVariable String name)
    {
        ShoppingListDto shoppingListDtoResult=shoppingListService.updateShoppingList(name, id);
        return ResponseEntity.ok(shoppingListDtoResult);
    }

    @DeleteMapping("/shoppingLists/{id}")
    public  void deleteShoppingList(@PathVariable Long id)
    {
        shoppingListService.deleteShoppingList(id);
    }

    @DeleteMapping("/shoppingLists/product/{productId}")
    public void deleteShoppingListProduct(@PathVariable Long productId)
    {
        shoppingListService.deleteShoppingListProduct(productId);
    }


    @GetMapping("/shoppingLists/users/{shoppingListId}")
    public List<String> getMembersForShoppingList(@PathVariable final Long shoppingListId)
    {
        User user=(User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        return  shoppingListService.showMembersFromAList(shoppingListId,user.getId());
    }

}
