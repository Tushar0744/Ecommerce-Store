package com.example.ecommerce.model;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class Stats {
    private int totalItems;
    private double totalAmount;
    private double totalDiscountAmount;
    private List<DiscountCode> discountCodes;
}
