package com.example.ecommerce.service;

import com.example.ecommerce.model.DiscountCode;
import com.example.ecommerce.model.User;

import java.util.List;

public interface DiscountService {
    DiscountCode generateDiscountCodeIfEligible(User user);

    boolean validateDiscountCode(String code);

    void markDiscountCodeAsUsed(String code);

    List<DiscountCode> getDiscountCodes();
}
