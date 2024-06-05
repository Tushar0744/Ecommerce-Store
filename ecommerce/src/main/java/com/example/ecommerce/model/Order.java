package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {
    private Long id;
    private User user;
    private List<Item> items;
    private double totalAmount;
    private double discountedAmount;
    private boolean discountApplied;
}
