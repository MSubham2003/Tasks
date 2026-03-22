package com.subham.user_auth_svc.dto;

import com.subham.user_auth_svc.enums.Role;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.Data;

@Data
public class RegisterRequestDTO {

    @NotBlank(message = "name cant be blank")
    private String name;

    @NotBlank(message = "email cant be blank")
    @Email(message = "email must be in valid format")
    private String email;

    @NotBlank(message = "password cant be blank")
    @Pattern(
            regexp = "^(?=.*[A-Z])(?=.*\\d).{8,}$",
            message = "password must be minimum 8 characters with at least one uppercase letter and one digit"
    )
    private String password;

    private Role role;
}