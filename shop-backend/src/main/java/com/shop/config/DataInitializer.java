package com.shop.config;

import com.shop.entity.User;
import com.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer implements CommandLineRunner {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) {

        if (userRepository.count() > 0) {
            return;
        }

        List<User> users = List.of(

                User.builder()
                        .fullName("Admin")
                        .email("admin@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .phone("0900000001")
                        .address("Cần Thơ")
                        .role("ADMIN")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                User.builder()
                        .fullName("Võ Minh Trung")
                        .email("trung@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .phone("0988888888")
                        .address("Cần Thơ")
                        .role("CUSTOMER")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                User.builder()
                        .fullName("Nguyễn Văn An")
                        .email("an@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .phone("0901111111")
                        .address("An Giang")
                        .role("CUSTOMER")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                User.builder()
                        .fullName("Trần Thị Bình")
                        .email("binh@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .phone("0902222222")
                        .address("Sóc Trăng")
                        .role("CUSTOMER")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                User.builder()
                        .fullName("Lê Văn Cường")
                        .email("cuong@gmail.com")
                        .password(passwordEncoder.encode("123456"))
                        .phone("0903333333")
                        .address("Vĩnh Long")
                        .role("CUSTOMER")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()
        );

        userRepository.saveAll(users);

        System.out.println("===== USERS CREATED =====");
    }
}