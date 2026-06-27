package com.shop.dto.response;

import lombok.*;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CartResponse {

    private String cartId;

    private String userId;

    private List<CartItemResponse> items;

    private BigDecimal totalAmount;
}