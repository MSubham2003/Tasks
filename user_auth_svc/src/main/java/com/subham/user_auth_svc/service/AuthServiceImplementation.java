package com.subham.user_auth_svc.service;


import com.subham.user_auth_svc.dto.AuthResponseDTO;
import com.subham.user_auth_svc.dto.LoginRequestDTO;
import com.subham.user_auth_svc.dto.RegisterRequestDTO;
import com.subham.user_auth_svc.entity.User;
import com.subham.user_auth_svc.enums.Role;
import com.subham.user_auth_svc.exception.AuthException;
import com.subham.user_auth_svc.model.Response;
import com.subham.user_auth_svc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Slf4j
@Service
public class AuthServiceImplementation implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final PojoValidator validator;

    private static final String SUCCESS = "SUCCESS";

    public AuthServiceImplementation(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     JwtService jwtService,
                                     PojoValidator validator) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
        this.validator = validator;
    }

    @Override
    public Response register(RegisterRequestDTO request) {
        validator.validate(request);

        Optional<User> existing = userRepository.findByEmail(request.getEmail());
        if (existing.isPresent()) {
            log.info("Registration failed, email already exists: {}", request.getEmail());
            throw new AuthException(HttpStatusCode.valueOf(409),
                    "Email already registered: " + request.getEmail());
        }

        User user = new User();
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setRole(request.getRole() != null ? request.getRole() : Role.USER);

        User saved = userRepository.save(user);
        log.info("User registered successfully with email: {}", saved.getEmail());

        String token = jwtService.generateToken(saved.getEmail(), saved.getRole());

        AuthResponseDTO data = new AuthResponseDTO(
                token,
                saved.getId(),
                saved.getName(),
                saved.getEmail(),
                saved.getRole()
        );

        Response res = new Response();
        res.setStatus(SUCCESS);
        res.setData(data);
        return res;
    }

    @Override
    public Response login(LoginRequestDTO request) {
        validator.validate(request);

        Optional<User> userOpt = userRepository.findByEmail(request.getEmail());
        if (userOpt.isEmpty() ||
                !passwordEncoder.matches(request.getPassword(), userOpt.get().getPassword())) {
            log.info("Login failed for email: {}", request.getEmail());
            throw new AuthException(HttpStatusCode.valueOf(401), "Invalid email or password");
        }

        User user = userOpt.get();
        String token = jwtService.generateToken(user.getEmail(), user.getRole());
        log.info("Login successful for email: {}", user.getEmail());

        AuthResponseDTO data = new AuthResponseDTO(
                token,
                user.getId(),
                user.getName(),
                user.getEmail(),
                user.getRole()
        );

        Response res = new Response();
        res.setStatus(SUCCESS);
        res.setData(data);
        return res;
    }
}