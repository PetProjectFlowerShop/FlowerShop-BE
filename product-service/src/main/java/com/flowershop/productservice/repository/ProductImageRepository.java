package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.ProductImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface ProductImageRepository extends JpaRepository<ProductImage, Long> {
    Optional<ProductImage> findByProductIdAndIsMainTrue(Long productId);

    List<ProductImage> findAllByProductId(Long productId);

    @Query("SELECT pi.imageUrl FROM ProductImage pi WHERE pi.product.id = :productId")
    List<String> findAllImageUrlsByProductId(@Param("productId") Long productId);

    void deleteAllByProductId(Long productId);
}
