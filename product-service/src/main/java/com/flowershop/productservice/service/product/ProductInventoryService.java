package com.flowershop.productservice.service.product;

import com.flowershop.productservice.dto.orderservice.BouquetItemRequest;
import com.flowershop.productservice.dto.orderservice.BouquetItemResp;
import com.flowershop.productservice.entity.Product;
import com.flowershop.productservice.entity.WrappingOption;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductInventoryService {
    private final ProductServiceImpl productService;
    private final WrappingOptionService wrappingOptionService;

    @Transactional
    public List<BouquetItemResp> getProductsPrices (List<BouquetItemRequest> items) {
        List<BouquetItemResp> response = new ArrayList<>();
        for (BouquetItemRequest item : items) {
            long productId = item.getProductId();
            long wrappingId = item.getWrappingId();
            Product product = productService.getProductByIdForOrderService(productId);
            WrappingOption wrapping = wrappingOptionService.getWrappingById(wrappingId);
            BouquetItemResp itemResp = new BouquetItemResp(productId, wrappingId, item.getQuantity());
            itemResp.setProductPrice(product.getPrice());
            itemResp.setDiscountPercent(product.getDiscountPercent());
            itemResp.setWrappingPrice(wrapping.getExtraPrice());
            productService.updateProductQuantity(product, item.getQuantity());
            response.add(itemResp);
        }
        System.out.println(response);
        return response;
    }

}
