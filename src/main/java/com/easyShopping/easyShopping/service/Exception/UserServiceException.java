package com.easyShopping.easyShopping.service.Exception;

import lombok.Getter;

@Getter
public class UserServiceException extends RuntimeException {
    private UserServiceExceptionCode code;

    public UserServiceException(UserServiceExceptionCode code) {
        super(code.getMessage());
        this.code = code;
    }

    public UserServiceException(UserServiceExceptionCode code, Throwable cause) {
        super(code.getMessage(), cause);
        this.code = code;
    }

    public UserServiceException(Throwable cause) {
        super(cause);
    }
}