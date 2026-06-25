package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.BouquetType;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface BouquetTypeRepository extends JpaRepository<BouquetType, Long> {
    @EntityGraph(attributePaths = {"packagingTypes"})
    List<BouquetType> findAll();
}
