package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.model.Cart;
import com.example.ecommerce.model.Item;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.CartService;
import com.example.ecommerce.service.DiscountService;
import com.example.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
@Slf4j
public class CartController {
    @Autowired
    private CartService cartService;

    @Autowired
    private UserService userService;

    @PostMapping("/add")
    public ResponseDTO<Cart> addItemToCart(@RequestParam Long userId, @RequestBody Item item) {
        try {
            User user = userService.getUserById(userId);
            return ResponseDTO.success(cartService.addItemToCart(user, item));
        } catch (Exception e) {
            log.error("Error while adding item to cart", e.getMessage());
            return ResponseDTO.failure(e.getMessage());
        }

    }

    @PostMapping("/checkout")
    public ResponseDTO<Order> checkout(@RequestParam Long userId, @RequestParam(required = false) String discountCode) {
        try {
            User user = userService.getUserById(userId);
            return ResponseDTO.success(cartService.checkoutWithDiscountCode(user, discountCode));
        } catch (Exception e) {
            log.error("Error while checking out cart", e.getMessage());
            return ResponseDTO.failure(e.getMessage());
        }
    }

    @GetMapping("get-cart")
    public ResponseDTO<Cart> getCart(@RequestParam Long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseDTO.success(cartService.getCart(user));
        } catch (Exception e) {
            log.error("Error while getting cart", e.getMessage());
            return ResponseDTO.failure(e.getMessage());
        }
    }
}
