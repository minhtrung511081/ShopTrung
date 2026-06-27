package com.shop.service.impl;

import com.shop.dto.request.CreateOrderRequest;
import com.shop.dto.response.OrderDetailResponse;
import com.shop.dto.response.OrderResponse;
import com.shop.entity.Order;
import com.shop.entity.OrderItem;
import com.shop.entity.Product;
import com.shop.enums.OrderStatus;
import com.shop.enums.PaymentStatus;
import com.shop.mapper.OrderMapper;
import com.shop.repository.CartRepository;
import com.shop.repository.OrderRepository;
import com.shop.repository.ProductRepository;
import com.shop.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    private final CartRepository cartRepository;

    private final ProductRepository productRepository;

    @Override
    public OrderResponse createOrder(CreateOrderRequest request) {

        String userId = getCurrentUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseThrow(() ->
                        new RuntimeException("Cart is empty"));

        if(cart.getItems().isEmpty()){
            throw new RuntimeException("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        BigDecimal total = BigDecimal.ZERO;

        for(CartItem item : cart.getItems()){

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow(() ->
                            new RuntimeException("Product not found"));

            if(product.getQuantity() < item.getQuantity()){
                throw new RuntimeException(
                        product.getName() + " out of stock");
            }

            BigDecimal itemTotal =
                    product.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()));

            orderItems.add(
                    OrderItem.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .image(product.getImage())
                            .price(product.getPrice())
                            .quantity(item.getQuantity())
                            .totalPrice(itemTotal)
                            .build()
            );

            total = total.add(itemTotal);

            product.setQuantity(
                    product.getQuantity()-item.getQuantity());

            productRepository.save(product);
        }

        Order order =
                Order.builder()
                        .orderCode(generateOrderCode())
                        .userId(userId)
                        .receiverName(request.getReceiverName())
                        .phone(request.getPhone())
                        .address(request.getAddress())
                        .paymentMethod(request.getPaymentMethod())
                        .paymentStatus(PaymentStatus.UNPAID)
                        .status(OrderStatus.PENDING)
                        .items(orderItems)
                        .totalAmount(total)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build();

        orderRepository.save(order);

        cart.getItems().clear();

        cartRepository.save(cart);

        return OrderMapper.toResponse(order);

    }

    @Override
    public List<OrderResponse> getMyOrders() {

        String userId = getCurrentUserId();

        return orderRepository.findByUserId(userId)
                .stream()
                .map(OrderMapper::toResponse)
                .toList();

    }

    @Override
    public OrderDetailResponse getDetail(String orderId) {

        String userId = getCurrentUserId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        if(!order.getUserId().equals(userId)){
            throw new RuntimeException("Access denied");
        }

        return OrderMapper.toDetail(order);

    }

    @Override
    public void cancel(String orderId) {

        String userId = getCurrentUserId();

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new RuntimeException("Order not found"));

        if(!order.getUserId().equals(userId)){
            throw new RuntimeException("Access denied");
        }

        if(order.getStatus() != OrderStatus.PENDING){
            throw new RuntimeException("Cannot cancel");
        }

        for(OrderItem item : order.getItems()){

            Product product = productRepository.findById(item.getProductId())
                    .orElseThrow();

            product.setQuantity(
                    product.getQuantity()+item.getQuantity());

            productRepository.save(product);

        }

        order.setStatus(OrderStatus.CANCELLED);

        order.setUpdatedAt(LocalDateTime.now());

        orderRepository.save(order);

    }

    private String generateOrderCode() {

        return "OD" + System.currentTimeMillis();

    }

    private String getCurrentUserId() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        return authentication.getName();
    }
}
