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
public class DataSeeder implements CommandLineRunner {

    private final UserRepository userRepository;

    private final BCryptPasswordEncoder encoder;

    @Override
    public void run(String... args) {

        if (userRepository.count() > 0) {
            return;
        }

        List<User> users = List.of(

                User.builder()
                        .fullName("Admin")
                        .email("admin@gmail.com")
                        .password(encoder.encode("123456"))
                        .phone("0900000001")
                        .address("Cần Thơ")
                        .role("ADMIN")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                User.builder()
                        .fullName("Nguyễn Văn A")
                        .email("vana@gmail.com")
                        .password(encoder.encode("123456"))
                        .phone("0900000002")
                        .address("An Giang")
                        .role("CUSTOMER")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build(),

                User.builder()
                        .fullName("Trần Thị B")
                        .email("thib@gmail.com")
                        .password(encoder.encode("123456"))
                        .phone("0900000003")
                        .address("Cần Thơ")
                        .role("CUSTOMER")
                        .enabled(true)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build()

        );

        userRepository.saveAll(users);

        System.out.println("===== Seed User Success =====");
    }
}