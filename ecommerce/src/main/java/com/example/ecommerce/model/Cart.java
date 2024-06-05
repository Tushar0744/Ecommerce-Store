package com.example.ecommerce.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class Cart {
    private Long id;
    private User user;
    private List<Item> items = new ArrayList<>();
    private boolean discountApplied;
    private long itemIdCounter; // Add item ID counter specific to the cart


    public Cart(Long id, User user) {
        this.id = ++itemIdCounter;
        this.user = user;
    }

    public void addItem(Item item) {
        this.items.add(item);
    }

    public long getNextItemId() {
        return ++itemIdCounter;
    }
}
