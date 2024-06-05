package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.DiscountCode;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class DiscountServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private DiscountServiceImpl discountService;

    private Map<Long, List<DiscountCode>> userDiscountCodes = new HashMap<>();

    private User user;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "testUser");
    }

    @Test
    void testGenerateDiscountCodeIfEligible() {
        // Create 5 orders to satisfy the NTH_ORDER condition
        DiscountCode discountCode = createDiscountCode();
        assertNotNull(discountCode);
        assertFalse(discountCode.isUsed());

        List<DiscountCode> userDiscountCodes = discountService.getDiscountCodes();
        assertEquals(1, userDiscountCodes.size());
        assertTrue(userDiscountCodes.contains(discountCode));
    }



    @Test
    void testValidateDiscountCode() {
        DiscountCode discountCode = createDiscountCode();
        assertTrue(discountService.validateDiscountCode(discountCode.getCode()));
    }

    private DiscountCode createDiscountCode() {
        List<Order> orders = new ArrayList<>(List.of(new Order(), new Order(), new Order(), new Order(), new Order()));
        when(orderRepository.getOrdersByUser(user)).thenReturn(orders);
        return discountService.generateDiscountCodeIfEligible(user);
    }

    @Test
    void testMarkDiscountCodeAsUsed() {
        DiscountCode discountCode = createDiscountCode();
        discountService.markDiscountCodeAsUsed(discountCode.getCode());
        assertTrue(discountCode.isUsed());
    }

    @Test
    void testGetDiscountCodes() {
        List<DiscountCode> discountCodes = discountService.getDiscountCodes();
        assertNotNull(discountCodes);
    }
}
