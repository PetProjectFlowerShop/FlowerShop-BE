package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.Occasion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OccasionRepository extends JpaRepository <Occasion, Long>{
}
