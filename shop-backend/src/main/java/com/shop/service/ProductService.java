package com.shop.service;

import com.shop.dto.request.ProductRequest;
import com.shop.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    List<ProductResponse> getAll();

    ProductResponse getById(String id);

    ProductResponse update(String id, ProductRequest request);

    void delete(String id);

    List<ProductResponse> search(String keyword);

    List<ProductResponse> findByCategory(String categoryId);

}
