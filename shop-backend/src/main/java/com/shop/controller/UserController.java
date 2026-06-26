package com.shop.controller;

import com.shop.entity.User;
import com.shop.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository repository;

    @GetMapping
    public List<User> findAll() {
        return repository.findAll();
    }
}