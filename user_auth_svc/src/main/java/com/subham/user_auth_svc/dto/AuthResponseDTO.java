package com.subham.user_auth_svc.dto;

import com.subham.user_auth_svc.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AuthResponseDTO {

    private String token;
    private Long id;
    private String name;
    private String email;
    private Role role;
}