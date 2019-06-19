package com.easyShopping.easyShopping.service.Exception;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum ShoppingListServiceExceptionCode {
    SHOPPING_LIST_NOT_FOUND("Shopping list not found"),
    SHOPPING_LIST_HAS_USER("Shopping list has this user"),
    SHOPPING_LIST_HAS_NOT_USER("This user can't have acess to the list");

    private String message;
}
