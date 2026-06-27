package com.shop.controller;

import com.shop.dto.request.CreateOrderRequest;
import com.shop.dto.response.OrderDetailResponse;
import com.shop.dto.response.OrderResponse;
import com.shop.service.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public ResponseEntity<OrderResponse> create(
            @RequestBody @Valid CreateOrderRequest request){

        return ResponseEntity.ok(orderService.createOrder(request));
    }

    @GetMapping
    public ResponseEntity<List<OrderResponse>> myOrders(){

        return ResponseEntity.ok(orderService.getMyOrders());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrderDetailResponse> detail(
            @PathVariable String id){

        return ResponseEntity.ok(orderService.getDetail(id));
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<Void> cancel(
            @PathVariable String id){

        orderService.cancel(id);

        return ResponseEntity.ok().build();
    }
}
