package com.subham.user_auth_svc.controller;


import com.subham.user_auth_svc.model.Response;
import com.subham.user_auth_svc.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/users")
@Slf4j
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("profile")
    public ResponseEntity<Response> getProfile(Authentication authentication) {
        log.info("Profile request for user: {}", authentication.getName());
        return ResponseEntity.ok(userService.getProfile(authentication.getName()));
    }

    @GetMapping
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<Response> getAllUsers() {
        log.info("Fetch all users request received");
        return ResponseEntity.ok(userService.getAllUsers());
    }

    @DeleteMapping("{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        log.info("Delete request received for user id: {}", id);
        userService.deleteUser(id);
        return ResponseEntity.status(204).build();
    }
}