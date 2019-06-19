package com.easyShopping.easyShopping.service.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum SupermarketServiceExceptionCode {

    SUPERMARKET_NOT_FOUND("Supermarket not found"),
    SUPERMARKET_EXISTS("Supermarket exists");

    private String message;
}
