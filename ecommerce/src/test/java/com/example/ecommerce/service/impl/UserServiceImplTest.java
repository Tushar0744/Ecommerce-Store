package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private UserServiceImpl userService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }


    @Test
    void testCreateUser() {
        // Given
        User user = new User(null, "testUser");
        User savedUser = new User(1L, "testUser");

        // When
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        // Then
        User createdUser = userService.createUser("testUser");

        assertNotNull(createdUser);
        assertEquals(1L, createdUser.getId());
        assertEquals("testUser", createdUser.getUsername());
        verify(userRepository, times(1)).save(any(User.class));
    }

    @Test
    void testGetUserById() {
        User user = new User(1L, "testUser");
        when(userRepository.findById(1L)).thenReturn(user);

        User retrievedUser = userService.getUserById(1L);

        assertNotNull(retrievedUser);
        assertEquals(1L, retrievedUser.getId());
        verify(userRepository, times(1)).findById(1L);
    }

    @Test
    void testGetUserByUsername() {
        User user = new User(1L, "testUser");
        when(userRepository.findByUsername("testUser")).thenReturn(user);

        User retrievedUser = userService.getUserByUsername("testUser");

        assertNotNull(retrievedUser);
        assertEquals("testUser", retrievedUser.getUsername());
        verify(userRepository, times(1)).findByUsername("testUser");
    }
}
