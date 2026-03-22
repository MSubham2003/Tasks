package com.subham.user_auth_svc.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class AuthException extends RuntimeException{
    private final HttpStatusCode httpStatusCode;
    private final String errorMessage;

    public AuthException(HttpStatusCode code, String errMsg){
        this.httpStatusCode = code;
        this.errorMessage = errMsg;
    }

}
