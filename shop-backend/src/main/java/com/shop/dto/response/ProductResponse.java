package com.shop.dto.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

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
}
