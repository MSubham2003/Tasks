package com.subham.studentrecord.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class StudentException extends RuntimeException{
    private final HttpStatusCode httpStatusCode;
    private final String errorMessage;

    public StudentException(HttpStatusCode code, String errMsg){
        this.httpStatusCode = code;
        this.errorMessage = errMsg;
    }

}
