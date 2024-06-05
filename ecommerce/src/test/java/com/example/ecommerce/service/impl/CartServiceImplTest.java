package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.*;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.DiscountService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class CartServiceImplTest {

    @Mock
    private CartRepository cartRepository;

    @Mock
    private OrderRepository orderRepository;

    @Mock
    private DiscountService discountService;

    @InjectMocks
    private CartServiceImpl cartService;

    private User user;
    private Item item;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        user = new User(1L, "testUser");
        item = new Item("item1", 100.0);
    }

    @Test
    void testAddItemToCart() {
        Cart cart = new Cart(1L, user);
        when(cartRepository.getCart(user)).thenReturn(cart);

        Cart updatedCart = cartService.addItemToCart(user, item);

        assertNotNull(updatedCart);
        assertEquals(1, updatedCart.getItems().size());
        assertEquals(item, updatedCart.getItems().get(0));
        verify(cartRepository, times(1)).save(cart);
    }

    @Test
    void testCheckoutWithoutDiscount() {
        Cart cart = new Cart(1L, user);
        cart.addItem(item);
        when(cartRepository.getCart(user)).thenReturn(cart);

        Order order = cartService.checkout(user, false);

        assertNotNull(order);
        assertEquals(user, order.getUser());
        assertEquals(1, order.getItems().size());
        assertEquals(100.0, order.getTotalAmount());
        verify(orderRepository, times(1)).save(order);
        verify(cartRepository, times(1)).save(any(Cart.class));
    }

    @Test
    void testCheckoutWithDiscount() {
        Cart cart = new Cart(1L, user);
        cart.addItem(item);
        when(cartRepository.getCart(user)).thenReturn(cart);

        Order order = cartService.checkout(user, true);

        assertNotNull(order);
        assertEquals(user, order.getUser());
        assertEquals(1, order.getItems().size());
        assertEquals(90.0, order.getDiscountedAmount());
        assertTrue(order.isDiscountApplied());
        verify(orderRepository, times(1)).save(order);
        verify(cartRepository, times(2)).save(any(Cart.class));
    }

    @Test
    void testCheckoutWithDiscountCode() {
        Cart cart = new Cart(1L, user);
        cart.addItem(item);
        when(cartRepository.getCart(user)).thenReturn(cart);
        when(discountService.validateDiscountCode("DISCOUNT10")).thenReturn(true);

        Order order = cartService.checkoutWithDiscountCode(user, "DISCOUNT10");

        assertNotNull(order);
        assertTrue(order.isDiscountApplied());
        verify(discountService, times(1)).markDiscountCodeAsUsed("DISCOUNT10");
    }

    @Test
    void testGetCart() {
        Cart cart = new Cart(1L, user);
        when(cartRepository.getCart(user)).thenReturn(cart);

        Cart retrievedCart = cartService.getCart(user);

        assertNotNull(retrievedCart);
        assertEquals(user, retrievedCart.getUser());
        verify(cartRepository, times(1)).getCart(user);
    }
}
