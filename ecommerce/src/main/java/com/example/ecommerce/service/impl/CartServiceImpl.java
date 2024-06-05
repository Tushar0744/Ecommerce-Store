package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.repository.CartRepository;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.DiscountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscountService discountService;

    private long itemIdCounter = 1; // Add an item ID counter

    private long cartIdCounter = 1;


    @Override
    public Cart addItemToCart(User user, Item item) {
        item.setId(itemIdCounter++); // Automatically assign item ID
        Cart cart = cartRepository.getCart(user);
        if (cart == null) {
            cart = new Cart(++itemIdCounter, user);
        }
        cart.addItem(item);
        cartRepository.save(cart);
        return cart;
    }

    @Override
    public Order checkout(User user, boolean applyDiscount) {
        Cart cart = cartRepository.getCart(user);
        Order order = new Order();
        order.setUser(user);
        order.setItems(cart.getItems());
        double totalAmount = cart.getItems().stream().mapToDouble(Item::getPrice).sum();
        order.setTotalAmount(totalAmount);

        if (applyDiscount && !cart.isDiscountApplied()) {
            totalAmount *= 0.9;
            order.setDiscountApplied(true);
            cart.setDiscountApplied(true);
            cartRepository.save(cart);
        }
        order.setDiscountedAmount(totalAmount);

        orderRepository.save(order);

        // Clear the cart after checkout
        cart = new Cart(null, user);
        cartRepository.save(cart);

        return order;
    }

    @Override
    public Order checkoutWithDiscountCode(User user, String discountCode) {
        boolean applyDiscount = discountCode != null && discountService.validateDiscountCode(discountCode);
        Order order = checkout(user, applyDiscount);

        if (applyDiscount) {
            discountService.markDiscountCodeAsUsed(discountCode);
        }

        return order;
    }

    @Override
    public Cart getCart(User user) {
        return cartRepository.getCart(user);
    }
}
