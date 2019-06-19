package com.easyShopping.easyShopping.service.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public  enum ProductListServiceExceptionCode {
    LIST_PRODUCT_NOT_FOUND("List product not found");

    private String message;
}
