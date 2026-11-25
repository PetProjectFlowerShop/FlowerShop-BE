package com.flowershop.productservice.repository;

import com.flowershop.productservice.entity.WrappingOption;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WrappingOptionRepository extends JpaRepository<WrappingOption, Long> {
    List<WrappingOption> findAllByIdIn(List<Long> ids);
}
