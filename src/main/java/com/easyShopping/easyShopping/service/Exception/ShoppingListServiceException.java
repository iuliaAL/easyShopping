package com.easyShopping.easyShopping.service.Exception;

public class ShoppingListServiceException extends RuntimeException {

    private  ShoppingListServiceExceptionCode code;

        public  ShoppingListServiceException(ShoppingListServiceExceptionCode code) {
            super(code.getMessage());
            this.code = code;
        }

        public  ShoppingListServiceException( ShoppingListServiceExceptionCode code, Throwable cause) {
            super(code.getMessage(), cause);
            this.code = code;
        }

        public  ShoppingListServiceException(Throwable cause) {
            super(cause);
        }
}

