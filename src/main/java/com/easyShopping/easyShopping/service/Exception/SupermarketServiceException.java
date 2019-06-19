package com.easyShopping.easyShopping.service.Exception;

public class SupermarketServiceException extends RuntimeException {
        private  SupermarketServiceExceptionCode code;

    public  SupermarketServiceException( SupermarketServiceExceptionCode code) {
            super(code.getMessage());
            this.code = code;
        }

    public  SupermarketServiceException( SupermarketServiceExceptionCode code, Throwable cause) {
            super(code.getMessage(), cause);
            this.code = code;
        }

    public  SupermarketServiceException(Throwable cause) {
            super(cause);
        }
    }

