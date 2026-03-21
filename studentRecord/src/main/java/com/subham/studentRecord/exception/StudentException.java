package com.subham.studentRecord.exception;

import lombok.Getter;
import org.springframework.http.HttpStatusCode;

@Getter
public class StudentException extends Exception{
    private final HttpStatusCode HTTP_STATUS_CODE;
    private final String ERROR_MESSAGE;

    public StudentException(HttpStatusCode code, String errMsg){
        this.HTTP_STATUS_CODE = code;
        this.ERROR_MESSAGE = errMsg;
    }

}
