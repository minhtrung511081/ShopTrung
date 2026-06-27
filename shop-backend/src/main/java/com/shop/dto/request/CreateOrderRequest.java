package com.shop.dto.request;

import com.shop.enums.PaymentMethod;
import lombok.Data;

@Data
public class CreateOrderRequest {

    private String receiverName;

    private String phone;

    private String address;

    private PaymentMethod paymentMethod;

}