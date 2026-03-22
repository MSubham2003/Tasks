package com.subham.user_auth_svc.service;


import com.subham.user_auth_svc.dto.LoginRequestDTO;
import com.subham.user_auth_svc.dto.RegisterRequestDTO;
import com.subham.user_auth_svc.model.Response;

public interface AuthService {
    Response register(RegisterRequestDTO request);
    Response login(LoginRequestDTO request);
}