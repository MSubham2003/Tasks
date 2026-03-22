package com.subham.user_auth_svc.service;


import com.subham.user_auth_svc.dto.UserResponseDTO;
import com.subham.user_auth_svc.entity.User;
import com.subham.user_auth_svc.exception.AuthException;
import com.subham.user_auth_svc.model.Response;
import com.subham.user_auth_svc.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Slf4j
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    private static final String SUCCESS = "SUCCESS";

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Response getProfile(String email) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty()) {
            log.info("Profile fetch failed, user not found for email: {}", email);
            throw new AuthException(HttpStatusCode.valueOf(404),
                    "User not found with email: " + email);
        }

        User user = userOpt.get();
        log.info("Profile fetched for email: {}", email);

        UserResponseDTO data = new UserResponseDTO(
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

    @Override
    public Response getAllUsers() {
        List<User> users = userRepository.findAll();
        log.info("Fetched all users, count: {}", users.size());

        List<UserResponseDTO> data = users.stream()
                .map(u -> new UserResponseDTO(
                        u.getId(),
                        u.getName(),
                        u.getEmail(),
                        u.getRole()))
                .toList();

        Response res = new Response();
        res.setStatus(SUCCESS);
        res.setData(data);
        return res;
    }

    @Override
    public void deleteUser(Long id) {
        Optional<User> userOpt = userRepository.findById(id);
        if (userOpt.isEmpty()) {
            log.info("Delete failed, user not found with id: {}", id);
            throw new AuthException(HttpStatusCode.valueOf(404),
                    "User not found with id: " + id);
        }
        userRepository.deleteById(id);
        log.info("User deleted with id: {}", id);
    }
}