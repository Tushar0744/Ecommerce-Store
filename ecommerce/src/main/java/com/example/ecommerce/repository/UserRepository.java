package com.example.ecommerce.repository;

import com.example.ecommerce.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class UserRepository {
    private Map<Long, User> users = new HashMap<>();
    private Long currentId = 1L;

    public User save(User user) {
        if (user.getId() == null) {
            user.setId(currentId++);
        }
        users.put(user.getId(), user);
        return user;
    }

    public User findById(Long id) {
        return users.get(id);
    }

    public User findByUsername(String username) {
        return users.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst()
                .orElse(null);
    }
}
