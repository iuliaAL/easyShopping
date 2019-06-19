package com.easyShopping.easyShopping.service.Exception;

public class SupermarketProductServiceException extends RuntimeException {

    private  SupermarketProductServiceExceptionCode code;

    public  SupermarketProductServiceException (SupermarketProductServiceExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public  SupermarketProductServiceException( SupermarketProductServiceExceptionCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
    }

    public  SupermarketProductServiceException(Throwable cause) {
        super(cause);
    }
}
