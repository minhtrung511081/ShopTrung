package com.shop.controller;

import com.shop.dto.request.ProductRequest;
import com.shop.dto.response.ProductResponse;
import com.shop.service.ProductService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
@CrossOrigin(origins = "*")
public class ProductController {

    private final ProductService productService;

    // Thêm sản phẩm
    @PostMapping
    public ProductResponse create(@Valid @RequestBody ProductRequest request) {
        return productService.create(request);
    }

    // Lấy tất cả sản phẩm
    @GetMapping
    public List<ProductResponse> getAll() {
        return productService.getAll();
    }

    // Lấy sản phẩm theo id
    @GetMapping("/{id}")
    public ProductResponse getById(@PathVariable String id) {
        return productService.getById(id);
    }

    // Cập nhật sản phẩm
    @PutMapping("/{id}")
    public ProductResponse update(
            @PathVariable String id,
            @Valid @RequestBody ProductRequest request) {

        return productService.update(id, request);
    }

    // Xóa mềm
    @DeleteMapping("/{id}")
    public String delete(@PathVariable String id) {
        productService.delete(id);
        return "Xóa thành công";
    }

    // Tìm kiếm theo tên
    @GetMapping("/search")
    public List<ProductResponse> search(
            @RequestParam String keyword) {

        return productService.search(keyword);
    }

    // Lọc theo danh mục
    @GetMapping("/category/{categoryId}")
    public List<ProductResponse> findByCategory(
            @PathVariable String categoryId) {

        return productService.findByCategory(categoryId);
    }

}
