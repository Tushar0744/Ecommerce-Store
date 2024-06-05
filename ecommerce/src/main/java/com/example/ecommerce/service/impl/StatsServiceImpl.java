package com.example.ecommerce.service.impl;

import com.example.ecommerce.model.DiscountCode;
import com.example.ecommerce.model.Order;
import com.example.ecommerce.model.Stats;
import com.example.ecommerce.repository.OrderRepository;
import com.example.ecommerce.service.DiscountService;
import com.example.ecommerce.service.StatsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StatsServiceImpl implements StatsService {
    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private DiscountService discountService;

    @Override
    public Stats calculateStats() {
        List<Order> orders = orderRepository.getOrders();
        List<DiscountCode> discountCodes = discountService.getDiscountCodes();

        int totalItems = orders.stream().mapToInt(order -> order.getItems().size()).sum();
        double totalAmount = orders.stream().mapToDouble(Order::getTotalAmount).sum();
        double totalDiscountAmount = orders.stream().mapToDouble(Order::getDiscountedAmount).sum();

        Stats stats = new Stats();
        stats.setTotalItems(totalItems);
        stats.setTotalAmount(totalAmount);
        stats.setTotalDiscountAmount(totalAmount - totalDiscountAmount);
        stats.setDiscountCodes(discountCodes);

        return stats;
    }
}
