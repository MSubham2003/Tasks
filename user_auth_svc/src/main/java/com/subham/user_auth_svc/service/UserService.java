package com.subham.user_auth_svc.service;


import com.subham.user_auth_svc.model.Response;

public interface UserService {
    Response getProfile(String email);
    Response getAllUsers();
    void deleteUser(Long id);
}