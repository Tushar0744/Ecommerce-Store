package com.example.ecommerce.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DiscountCode {
    private String code;
    private boolean isUsed;

    public DiscountCode(String string, boolean b) {
        code = string;
        isUsed = b;
    }

    public DiscountCode() {
    }
}
