package com.subham.user_auth_svc.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class LoginRequestDTO {

    @NotBlank(message = "email cant be blank")
    @Email(message = "email must be in valid format")
    private String email;

    @NotBlank(message = "password cant be blank")
    private String password;
}