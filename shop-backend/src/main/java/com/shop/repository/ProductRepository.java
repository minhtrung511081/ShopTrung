package com.shop.repository;

import com.shop.entity.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product,String> {

    List<Product> findByCategoryId(String categoryId);

    List<Product> findByNameContainingIgnoreCase(String keyword);


}