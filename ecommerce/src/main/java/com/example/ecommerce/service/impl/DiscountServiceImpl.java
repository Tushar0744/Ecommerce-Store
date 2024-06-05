package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.DiscountCode;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.DiscountService;
import com.example.ecommerce.repository.OrderRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@Slf4j
public class DiscountServiceImpl implements DiscountService {
    private Map<Long, List<DiscountCode>> userDiscountCodes = new HashMap<>();
    private static final int NTH_ORDER = 5;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public DiscountCode generateDiscountCodeIfEligible(User user) {
        int orderCount = orderRepository.getOrdersByUser(user).size();
        if (orderCount > 0 && orderCount % NTH_ORDER == 0) {
            DiscountCode discountCode = new DiscountCode();
            discountCode.setCode(UUID.randomUUID().toString());
            discountCode.setUsed(false);

            log.info(discountCode.getCode().toString());
            userDiscountCodes.computeIfAbsent(user.getId(), k -> new ArrayList<>()).add(discountCode);
            return discountCode;
        }
        return null;
    }

    @Override
    public boolean validateDiscountCode(String code) {
        return userDiscountCodes.values().stream()
                .flatMap(List::stream)
                .anyMatch(discountCode -> discountCode.getCode().equals(code) && !discountCode.isUsed());
    }

    @Override
    public void markDiscountCodeAsUsed(String code) {
        userDiscountCodes.values().stream()
                .flatMap(List::stream)
                .filter(discountCode -> discountCode.getCode().equals(code))
                .forEach(discountCode -> discountCode.setUsed(true));
    }

    @Override
    public List<DiscountCode> getDiscountCodes() {
        return userDiscountCodes.values().stream().flatMap(List::stream).collect(Collectors.toList());
    }
}
