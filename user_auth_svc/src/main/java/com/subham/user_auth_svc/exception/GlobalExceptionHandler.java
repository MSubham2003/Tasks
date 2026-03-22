package com.subham.user_auth_svc.exception;

import com.subham.user_auth_svc.model.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    private static final String STATUS = "FAILED";

    @ExceptionHandler({RuntimeException.class})
    public ResponseEntity<Response> handleRunTimeException(AuthException ex){
        log.info("User Exception: {}", ex.getErrorMessage());
        Response res = new Response();
        res.setStatus(STATUS);
        res.setErrMessage(ex.getErrorMessage());
        HttpStatusCode httpStatusCode = ex.getHttpStatusCode();
        log.info("Response Returned: {}", res);
        return ResponseEntity.status(httpStatusCode).body(res);
    }

    @ExceptionHandler({Exception.class})
    public ResponseEntity<Response> handleException(Exception ex){
        log.info("Unhandled User Exception: ", ex);
        Response res = new Response();
        res.setStatus(STATUS);
        res.setErrMessage(ex.getMessage());
        log.info("Response Returned: {}", res);
        return ResponseEntity.status(500).body(res);
    }
}
