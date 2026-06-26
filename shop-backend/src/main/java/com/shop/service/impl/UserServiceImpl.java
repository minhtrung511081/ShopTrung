package com.shop.service.impl;

import com.shop.dto.request.LoginRequest;
import com.shop.dto.request.RegisterRequest;
import com.shop.dto.response.AuthResponse;
import com.shop.entity.User;
import com.shop.repository.UserRepository;
import com.shop.security.JwtService;
import com.shop.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Override
    public AuthResponse login(LoginRequest request) {

        User user = userRepository.findByEmail(request.getEmail()).orElse(null);

        // Email không tồn tại
        if (user == null) {
            return AuthResponse.builder()
                    .success(false)
                    .message("Email không tồn tại")
                    .build();
        }

        // Tài khoản bị khóa
        if (!Boolean.TRUE.equals(user.getEnabled())) {
            return AuthResponse.builder()
                    .success(false)
                    .message("Tài khoản đã bị khóa")
                    .build();
        }

        System.out.println("Request password: " + request.getPassword());
        System.out.println("DB password: " + user.getPassword());

        boolean result = passwordEncoder.matches(
                request.getPassword(),
                user.getPassword()
        );

        System.out.println("Match: " + result);

        if (!result) {
            return AuthResponse.builder()
                    .success(false)
                    .message("Sai mật khẩu")
                    .build();
        }

        String token = jwtService.generateToken(user.getEmail());

        return AuthResponse.builder()
                .success(true)
                .message("Đăng nhập thành công")
                .token(token)
                .fullName(user.getFullName())
                .email(user.getEmail())
                .role(user.getRole())
                .build();
    }

    @Override
    public String register(RegisterRequest request) {

        if (userRepository.existsByEmail(request.getEmail())) {
            return "Email đã tồn tại";
        }

        User user = User.builder()
                .fullName(request.getFullName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .phone(request.getPhone())
                .address(request.getAddress())
                .role("CUSTOMER")
                .enabled(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        userRepository.save(user);

        return "Đăng ký thành công";
    }
}