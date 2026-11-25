package com.flowershop.productservice.service.product;

import com.flowershop.productservice.entity.WrappingOption;
import com.flowershop.productservice.exceptions.WrappingNotFoundException;
import com.flowershop.productservice.repository.WrappingOptionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class WrappingOptionService {
    private final WrappingOptionRepository wrappingOptionRepository;

    public WrappingOption getWrappingById(long id) {
        return wrappingOptionRepository.findById(id).orElseThrow(()
            -> new WrappingNotFoundException("Wrapping with id: " + id + " doesnt exist"));
    }

}
