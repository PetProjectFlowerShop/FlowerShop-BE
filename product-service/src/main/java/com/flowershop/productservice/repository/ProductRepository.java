package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @EntityGraph(attributePaths = {"colors", "flowerTypes", "images", "occasions", "bouquetType.packagingTypes"})
    Optional<Product> findById(Long id);

    @EntityGraph(attributePaths = {"images", "colors", "occasions", "flowerTypes"})
    Page<Product> findAll(Specification<Product> spec, Pageable page);

    @Query(value = "SELECT p.id FROM Product p WHERE p.isRecommended = true ORDER BY RANDOM() LIMIT 4")
    List<Long> findRandomRecommendedIds();

    @EntityGraph(attributePaths = "images")
    @Query("SELECT p FROM Product p WHERE p.id IN :ids")
    List<Product> findAllByIds(@Param("ids") List<Long> ids);

    @EntityGraph(attributePaths = {"images", "colors", "occasions", "flowerTypes"})
    List<Product> findAllByIdIn(Collection<Long> ids);

}
