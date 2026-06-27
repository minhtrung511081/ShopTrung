package com.shop.service;

import com.shop.dto.request.AddToCartRequest;
import com.shop.dto.request.UpdateCartRequest;
import com.shop.dto.response.CartResponse;

public interface CartService {

    CartResponse addToCart(AddToCartRequest request);

    CartResponse getMyCart();

    CartResponse updateQuantity(UpdateCartRequest request);

    void removeItem(String productId);

    void clearCart();
}
