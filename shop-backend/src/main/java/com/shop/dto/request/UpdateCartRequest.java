package com.shop.dto.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateCartRequest {

    @NotBlank
    private String productId;

    @Min(1)
    private Integer quantity;
}
