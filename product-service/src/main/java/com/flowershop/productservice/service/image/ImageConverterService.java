package com.flowershop.productservice.service.image;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;

@Service
public class ImageConverterService {
    public byte[] convertToWebp(MultipartFile file) throws IOException {

        BufferedImage image = ImageIO.read(file.getInputStream());

        if (image == null) {
            throw new IllegalArgumentException("File is not an image");
        }

        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        boolean success = ImageIO.write(image, "webp", baos);

        if (!success) {
            throw new RuntimeException("WebP writer not found");
        }

        return baos.toByteArray();
    }
}
