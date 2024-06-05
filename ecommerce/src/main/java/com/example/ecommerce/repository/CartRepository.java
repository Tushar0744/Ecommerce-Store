package com.example.ecommerce.repository;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.User;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class CartRepository {
    private Map<Long, Cart> userCarts = new HashMap<>();

    public Cart getCart(User user) {
        return userCarts.getOrDefault(user.getId(), new Cart(null, user));
    }

    public void save(Cart cart) {
        userCarts.put(cart.getUser().getId(), cart);
    }
}
