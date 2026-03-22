package com.subham.user_auth_svc.controller;


import com.subham.user_auth_svc.dto.LoginRequestDTO;
import com.subham.user_auth_svc.dto.RegisterRequestDTO;
import com.subham.user_auth_svc.model.Response;
import com.subham.user_auth_svc.service.AuthService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@Slf4j
public class AuthController {

    private final AuthService authService;

    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("register")
    public ResponseEntity<Response> register(@RequestBody RegisterRequestDTO request) {
        log.info("Register request received for email: {}", request.getEmail());
        return ResponseEntity.status(201).body(authService.register(request));
    }

    @PostMapping("login")
    public ResponseEntity<Response> login(@RequestBody LoginRequestDTO request) {
        log.info("Login request received for email: {}", request.getEmail());
        return ResponseEntity.ok(authService.login(request));
    }
}