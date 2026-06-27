package com.shop.dto.response;

import com.shop.enums.OrderStatus;
import com.shop.enums.PaymentMethod;
import com.shop.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderResponse {

    private String id;

    private String orderCode;

    private BigDecimal totalAmount;

    private OrderStatus status;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private LocalDateTime createdAt;
}
