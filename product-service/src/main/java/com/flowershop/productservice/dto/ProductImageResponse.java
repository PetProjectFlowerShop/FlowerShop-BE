package com.flowershop.productservice.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductImageResponse implements Serializable {
    private Long id;
    private String imageUrl;
    private Boolean isMain;
    private Long productId;
}
