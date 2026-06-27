package com.shop.entity;

import com.shop.enums.OrderStatus;
import com.shop.enums.PaymentMethod;
import com.shop.enums.PaymentStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Document(collection = "orders")
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    private String id;

    private String orderCode;

    private String userId;

    private String receiverName;

    private String phone;

    private String address;

    private PaymentMethod paymentMethod;

    private PaymentStatus paymentStatus;

    private OrderStatus status;

    private BigDecimal totalAmount;

    private List<OrderItem> items;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}