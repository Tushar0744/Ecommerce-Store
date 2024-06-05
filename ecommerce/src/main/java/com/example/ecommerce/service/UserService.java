package com.example.ecommerce.service;

import com.example.ecommerce.model.User;

public interface UserService {
    User createUser(String username);
    User getUserById(Long id);
    User getUserByUsername(String username);
}
