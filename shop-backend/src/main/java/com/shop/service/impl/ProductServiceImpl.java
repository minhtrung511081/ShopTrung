package com.shop.service.impl;

import com.shop.dto.request.ProductRequest;
import com.shop.dto.response.ProductResponse;
import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public ProductResponse create(ProductRequest request) {

        Product product = Product.builder()
                .name(request.getName())
                .description(request.getDescription())
                .price(request.getPrice())
                .quantity(request.getQuantity())
                .image(request.getImage())
                .categoryId(request.getCategoryId())
                .status(true)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        product = productRepository.save(product);

        return ProductResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .image(product.getImage())
                .categoryId(product.getCategoryId())
                .status(product.getStatus())
                .build();
    }

    @Override
    public List<ProductResponse> getAll() {
        return productRepository.findAll()
                .stream()
                .map(product -> ProductResponse.builder()
                        .id(product.getId())
                        .name(product.getName())
                        .description(product.getDescription())
                        .price(product.getPrice())
                        .quantity(product.getQuantity())
                        .image(product.getImage())
                        .categoryId(product.getCategoryId())
                        .status(product.getStatus())
                        .build())
                .toList();
    }

    @Override
    public ProductResponse getById(String id) {
        throw new UnsupportedOperationException("Chưa triển khai");
    }

    @Override
    public ProductResponse update(String id, ProductRequest request) {
        throw new UnsupportedOperationException("Chưa triển khai");
    }

    @Override
    public void delete(String id) {
        throw new UnsupportedOperationException("Chưa triển khai");
    }

    @Override
    public List<ProductResponse> search(String keyword) {
        throw new UnsupportedOperationException("Chưa triển khai");
    }

    @Override
    public List<ProductResponse> findByCategory(String categoryId) {
        throw new UnsupportedOperationException("Chưa triển khai");
    }
}