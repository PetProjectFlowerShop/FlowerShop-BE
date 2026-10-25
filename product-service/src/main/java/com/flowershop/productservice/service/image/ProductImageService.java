package com.flowershop.productservice.service.image;

import com.flowershop.productservice.dto.ProductImageResponse;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

/**
 * Service interface for managing product-related images
 */
public interface ProductImageService {

    /**
     * Uploads multiple files to storage and associates them with a specific product.
     *
     * @param productId the unique identifier of the product.
     * @param files     the list of multipart files to be uploaded.
     * @return a list of {@link ProductImageResponse} containing image metadata and URLs.
     */
    List<ProductImageResponse> addImages(Long productId, MultipartFile[] files) throws IOException;

    /**
     * Sets a specific image as the primary (main) image for its product.
     *
     * @param productId the unique identifier of the product.
     * @param imageId   the unique identifier of the image to be set as main.
     */
    ProductImageResponse setMainImage(Long productId, Long imageId);

    /**
     * Retrieves all images associated with a specific product.
     *
     * @param productId the unique identifier of the product.
     * @return a list of {@link ProductImageResponse} containing image details.
     */
    List<ProductImageResponse> getProductImages(Long productId);

    /**
     * Deletes a specific image from both the database and cloud storage.
     *
     * @param imageId the unique identifier of the image to be removed.
     */
    void deleteImage(Long imageId);

    /**
     * Deletes all images associated with a specific product from both the database and cloud storage.
     *
     * @param productId the unique identifier of the product whose images should be removed.
     */
    void deleteAllProductImages(Long productId);
}
