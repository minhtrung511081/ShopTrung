package com.shop.dto.request;

import jakarta.validation.constraints.*;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ProductRequest {

    @NotBlank(message = "Tên sản phẩm không được để trống")
    private String name;

    @NotBlank(message = "Mô tả không được để trống")
    private String description;

    @NotNull
    @DecimalMin("0")
    private BigDecimal price;

    @NotNull
    @Min(0)
    private Integer quantity;

    @NotBlank
    private String image;

    @NotBlank
    private String categoryId;

    private Boolean status;

}