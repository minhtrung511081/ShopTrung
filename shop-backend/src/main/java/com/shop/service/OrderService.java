package com.shop.service;

import com.shop.dto.request.CreateOrderRequest;
import com.shop.dto.response.OrderDetailResponse;
import com.shop.dto.response.OrderResponse;

import java.util.List;

public interface OrderService {

    OrderResponse createOrder(CreateOrderRequest request);

    List<OrderResponse> getMyOrders();

    OrderDetailResponse getDetail(String orderId);

    void cancel(String orderId);

}
