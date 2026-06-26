package com.shop.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Data
@Builder
public class ProductResponse {

    private String id;

    private String name;

    private String description;

    private BigDecimal price;

    private Integer quantity;

    private String image;

    private String categoryId;

    private Boolean status;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}
