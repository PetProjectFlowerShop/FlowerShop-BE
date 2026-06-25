package com.flowershop.productservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.io.Serializable;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProductImageResponse implements Serializable {
    private Long id;
    private String imageUrl;
    private Boolean isMain;
    private Long productId;
}
