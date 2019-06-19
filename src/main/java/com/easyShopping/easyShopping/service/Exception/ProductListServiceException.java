package com.easyShopping.easyShopping.service.Exception;

public class ProductListServiceException extends RuntimeException {

    private  ProductListServiceExceptionCode code;

    public  ProductListServiceException(ProductListServiceExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public  ProductListServiceException( ProductListServiceExceptionCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
    }

    public  ProductListServiceException(Throwable cause) {
        super(cause);
    }
}
