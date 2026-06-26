package com.shop.shop_backend.service;

import com.shop.shop_backend.dto.request.ProductRequest;
import com.shop.shop_backend.dto.response.ProductResponse;

import java.util.List;

public interface ProductService {

    ProductResponse create(ProductRequest request);

    List<ProductResponse> getAll();

}
