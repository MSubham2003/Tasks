package com.subham.studentRecord.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import org.aspectj.bridge.IMessage;

import java.time.LocalDateTime;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @JsonProperty("id")
    private Long id;

    @JsonProperty("name")
    @NotBlank(message = "Name cant be blank")
    private String name;

    @JsonProperty("email")
    @NotBlank(message = "email cant be blank")
    @Pattern(regexp = "^[a-zA-Z0-9._-]+@gmail\\.com$", message = "email must be in valid format")
    private String email;

    @JsonProperty("phone")
    @Pattern(regexp = "^(?!0)(?!\\+91)[1-9][0-9]{9}$", message = "Mobile number must be exactly 10 digits and must not include 0 or +91 prefix")
    private String phone;

    @JsonProperty("department")
    private String department;

    @JsonProperty("createdAt")
    private LocalDateTime createdAt = LocalDateTime.now();

}
