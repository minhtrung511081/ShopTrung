package com.shop.dto.response;

import com.shop.enums.OrderStatus;
import com.shop.enums.PaymentMethod;
import com.shop.enums.PaymentStatus;
import lombok.*;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderDetailResponse {

    private String id;

    private String orderCode;

    private String receiverName;

    private String phone;

    private String address;

    private BigDecimal totalAmount;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private OrderStatus status;

    private List<OrderItemResponse> items;

    private LocalDateTime createdAt;
}