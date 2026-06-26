package com.shop.controller;

import com.shop.dto.request.LoginRequest;
import com.shop.dto.request.RegisterRequest;
import com.shop.dto.response.AuthResponse;
import com.shop.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
@Tag(name = "Authentication")
public class AuthController {

    private final UserService userService;

    @Operation(summary = "Đăng ký tài khoản")
    @PostMapping("/register")
    public String register(
            @RequestBody RegisterRequest request){

        return userService.register(request);

    }

    @Operation(summary = "Đăng nhập")
    @PostMapping("/login")
    public AuthResponse login(
            @RequestBody LoginRequest request){

        return userService.login(request);

    }

}