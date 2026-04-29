package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findByProductIdAndIsMainTrue(Long productId);
    List<ProductImage> findAllByProductId(Long productId);
}
