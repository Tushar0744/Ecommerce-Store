package com.example.ecommerce.service;

import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;

public interface CartService {
    Cart addItemToCart(User user, Item item);
    Order checkout(User user, boolean applyDiscount);
    Order checkoutWithDiscountCode(User user, String discountCode);
    Cart getCart(User user);
}
