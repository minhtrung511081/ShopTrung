package com.shop.dto.response;

import lombok.*;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartItemResponse {

    private String productId;

    private String productName;

    private String image;

    private BigDecimal price;

    private Integer quantity;

    private BigDecimal totalPrice;
}