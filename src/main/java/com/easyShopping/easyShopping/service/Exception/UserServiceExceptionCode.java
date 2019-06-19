package com.easyShopping.easyShopping.service.Exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public enum UserServiceExceptionCode {
    USER_NOT_FOUND("User not found"),
    BAD_CREDENTIALS("Bad credentials."),
    USER_WITH_EMAIL_EXIST("A user with that email already exist"),
    EMAIL_INVALID("Email invalid");

    private String message;
}
