package com.subham.studentRecord.entity.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class StudentDto {

    @NotBlank(message = "Name cant be blank")
    private String name;

    @NotBlank(message = "email cant be blank")
    @Email(message = "Email should be in valid format")
    private String email;

    @Pattern(regexp = "^(?!0)(?!\\+91)[1-9][0-9]{9}$", message = "Mobile number must be exactly 10 digits and must not include 0 or +91 prefix")
    private String phone;

    private String department;
}
