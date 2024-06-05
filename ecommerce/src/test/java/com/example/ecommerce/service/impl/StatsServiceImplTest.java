package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.DiscountCode;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Stats;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class StatsServiceImplTest {

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private StatsServiceImpl statsService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCalculateStats() {
        // Create item lists for orders
        List<Item> itemsOrder1 = new ArrayList<>();
        itemsOrder1.add(new Item( "item1", 50.0));
        itemsOrder1.add(new Item( "item2", 50.0));

        List<Item> itemsOrder2 = new ArrayList<>();
        itemsOrder2.add(new Item( "item3", 100.0));
        itemsOrder2.add(new Item( "item4", 100.0));

        // Create orders with initialized item lists
        List<Order> orders = new ArrayList<>();
        orders.add(new Order(1L, new User(1L, "user1"), itemsOrder1, 100.0, 90.0, true));
        orders.add(new Order(2L, new User(2L, "user2"), itemsOrder2, 200.0, 180.0, true));

        // Create discount codes
        List<DiscountCode> discountCodes = new ArrayList<>();
        discountCodes.add(new DiscountCode("CODE1", true));
        discountCodes.add(new DiscountCode("CODE2", false));

        when(orderRepository.getOrders()).thenReturn(orders);
        when(discountService.getDiscountCodes()).thenReturn(discountCodes);

        // Calculate stats
        Stats stats = statsService.calculateStats();

        // Verify results
        assertNotNull(stats);
        assertEquals(4, stats.getTotalItems()); // Two items in each order
        assertEquals(300.0, stats.getTotalAmount());
        assertEquals(30.0, stats.getTotalDiscountAmount());
        assertEquals(2, stats.getDiscountCodes().size());
    }
}
