package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.FlowerType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FlowerTypeRepository extends JpaRepository<FlowerType,Long > {
}
