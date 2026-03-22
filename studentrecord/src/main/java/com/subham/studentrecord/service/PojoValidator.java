package com.subham.studentrecord.service;

import com.subham.studentrecord.exception.StudentException;
import jakarta.validation.ConstraintViolation;
import jakarta.validation.Validator;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;

import java.util.Set;
import java.util.stream.Collectors;

@Component
public class PojoValidator {

    private final Validator validator;

    public PojoValidator(Validator validator) {
        this.validator = validator;
    }

    public <T> void validate(T object) throws StudentException {
        Set<ConstraintViolation<T>> violations = validator.validate(object);
        if (!violations.isEmpty()) {
            String messages = violations.stream()
                    .map(ConstraintViolation::getMessage)
                    .collect(Collectors.joining(", "));
            throw new StudentException(HttpStatusCode.valueOf(400), messages);
        }
    }
}
