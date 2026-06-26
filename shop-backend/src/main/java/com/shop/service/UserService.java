package com.shop.service;

import com.shop.dto.request.LoginRequest;
import com.shop.dto.request.RegisterRequest;
import com.shop.dto.response.AuthResponse;

public interface UserService {

    String register(RegisterRequest request);

    AuthResponse login(LoginRequest request);

}