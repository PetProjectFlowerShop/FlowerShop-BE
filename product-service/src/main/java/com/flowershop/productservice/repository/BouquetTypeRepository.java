package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.BouquetType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BouquetTypeRepository extends JpaRepository<BouquetType,Long> {
}
