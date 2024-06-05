package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/create")
    public ResponseDTO<User> createUser(@RequestParam String username) {
        try {
            return ResponseDTO.success(userService.createUser(username));
        } catch (Exception e) {
            log.error("Error While creating user", e.getMessage());
            return ResponseDTO.failure(e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public User getUserById(@PathVariable Long id) {
        return userService.getUserById(id);
    }

    @GetMapping("/username")
    public User getUserByUsername(@RequestParam String username) {
        return userService.getUserByUsername(username);
    }
}
