package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.Product;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>, JpaSpecificationExecutor<Product> {
    @EntityGraph(attributePaths = {"images", "colors", "occasions", "flowerTypes"})
    Page<Product> findAll(Specification<Product> spec, Pageable page);

    @Query(value = "SELECT p FROM Product p "
        + "LEFT JOIN FETCH p.images "
        + "LEFT JOIN FETCH p.bouquetType bt "
        + "WHERE p.isRecommended = true "
        + "ORDER BY RANDOM() LIMIT 4")
    List<Product> findRandomRecommended();

    @EntityGraph(attributePaths = {"images", "colors", "occasions", "flowerTypes"})
    List<Product> findAllByIdIn(Collection<Long> ids);
}
