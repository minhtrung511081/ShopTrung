package com.shop.service.impl;

import com.shop.dto.request.AddToCartRequest;
import com.shop.dto.request.UpdateCartRequest;
import com.shop.dto.response.CartItemResponse;
import com.shop.dto.response.CartResponse;
import com.shop.entity.Cart;
import com.shop.entity.CartItem;
import com.shop.entity.Product;
import com.shop.entity.User;
import com.shop.repository.CartRepository;
import com.shop.repository.ProductRepository;
import com.shop.repository.UserRepository;
import com.shop.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

    private final CartRepository cartRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    private String getCurrentUserId() {

        Authentication authentication =
                SecurityContextHolder.getContext().getAuthentication();

        User user = userRepository
                .findByEmail(authentication.getName())
                .orElseThrow(() -> new RuntimeException("User not found"));

        return user.getId();
    }

    @Override
    public CartResponse getMyCart() {

        String userId = getCurrentUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> {
                    Cart c = Cart.builder()
                            .userId(userId)
                            .totalAmount(BigDecimal.ZERO)
                            .items(new ArrayList<>())
                            .createdAt(LocalDateTime.now())
                            .updatedAt(LocalDateTime.now())
                            .build();

                    return cartRepository.save(c);
                });

        return mapToResponse(cart);
    }

    @Override
    public CartResponse addToCart(AddToCartRequest request) {

        String userId = getCurrentUserId();

        Cart cart = cartRepository.findByUserId(userId)
                .orElseGet(() -> Cart.builder()
                        .userId(userId)
                        .items(new ArrayList<>())
                        .totalAmount(BigDecimal.ZERO)
                        .createdAt(LocalDateTime.now())
                        .updatedAt(LocalDateTime.now())
                        .build());

        Product product = productRepository.findById(request.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        Optional<CartItem> itemOptional = cart.getItems()
                .stream()
                .filter(i -> i.getProductId().equals(product.getId()))
                .findFirst();

        if(itemOptional.isPresent()){

            CartItem item = itemOptional.get();

            item.setQuantity(item.getQuantity()+request.getQuantity());

            item.setTotalPrice(
                    item.getPrice()
                            .multiply(BigDecimal.valueOf(item.getQuantity()))
            );

        }else{

            cart.getItems().add(
                    CartItem.builder()
                            .productId(product.getId())
                            .productName(product.getName())
                            .image(product.getImage())
                            .price(product.getPrice())
                            .quantity(request.getQuantity())
                            .totalPrice(
                                    product.getPrice().multiply(
                                            BigDecimal.valueOf(request.getQuantity())
                                    )
                            )
                            .build()
            );

        }

        calculateTotal(cart);

        cart.setUpdatedAt(LocalDateTime.now());

        cartRepository.save(cart);

        return mapToResponse(cart);

    }

    @Override
    public CartResponse updateQuantity(UpdateCartRequest request) {

        Cart cart = cartRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().forEach(item -> {

            if(item.getProductId().equals(request.getProductId())){

                item.setQuantity(request.getQuantity());

                item.setTotalPrice(
                        item.getPrice()
                                .multiply(BigDecimal.valueOf(request.getQuantity()))
                );

            }

        });

        calculateTotal(cart);

        cartRepository.save(cart);

        return mapToResponse(cart);

    }

    @Override
    public void removeItem(String productId) {

        Cart cart = cartRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().removeIf(item ->
                item.getProductId().equals(productId));

        calculateTotal(cart);

        cartRepository.save(cart);

    }

    @Override
    public void clearCart() {

        Cart cart = cartRepository.findByUserId(getCurrentUserId())
                .orElseThrow(() -> new RuntimeException("Cart not found"));

        cart.getItems().clear();

        cart.setTotalAmount(BigDecimal.ZERO);

        cartRepository.save(cart);

    }

    private void calculateTotal(Cart cart){

        BigDecimal total = BigDecimal.ZERO;

        for(CartItem item : cart.getItems()){

            total = total.add(item.getTotalPrice());

        }

        cart.setTotalAmount(total);

    }

    private CartResponse mapToResponse(Cart cart){

        return CartResponse.builder()
                .cartId(cart.getId())
                .userId(cart.getUserId())
                .totalAmount(cart.getTotalAmount())
                .items(
                        cart.getItems()
                                .stream()
                                .map(item -> CartItemResponse.builder()
                                        .productId(item.getProductId())
                                        .productName(item.getProductName())
                                        .image(item.getImage())
                                        .price(item.getPrice())
                                        .quantity(item.getQuantity())
                                        .totalPrice(item.getTotalPrice())
                                        .build())
                                .toList()
                )
                .build();

    }
}
