package com.example.ecommerce.controller;

import com.example.ecommerce.dto.ResponseDTO;
import com.example.ecommerce.model.DiscountCode;
import com.example.ecommerce.model.Stats;
import com.example.ecommerce.model.User;
import com.example.ecommerce.service.DiscountService;
import com.example.ecommerce.service.StatsService;
import com.example.ecommerce.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/admin")
@Slf4j
public class AdminController {
    @Autowired
    private DiscountService discountService;

    @Autowired
    private StatsService statsService;

    @Autowired
    private UserService userService;

    @GetMapping("/stats")
    public ResponseDTO<Stats> getStats() {
        try {
            return ResponseDTO.success(statsService.calculateStats());
        } catch (Exception e) {
            log.error("Error while calculating stats", e.getMessage());
            return ResponseDTO.failure(e.getMessage());
        }

    }

    @GetMapping("/generate-discount")
    public ResponseDTO<DiscountCode> generateDiscountCode(@RequestParam Long userId) {
        try {
            User user = userService.getUserById(userId);
            return ResponseDTO.success(discountService.generateDiscountCodeIfEligible(user));
        } catch (Exception e) {
            log.error("Error while generating discount code", e);
            return ResponseDTO.failure(e.getMessage());
        }

    }
}
