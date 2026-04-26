package com.flowershop.productservice.service.image;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.services.s3.S3Client;
import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AmazonS3Service implements FileStorageService{
    private final S3Client s3Client;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        return "";
    }

    @Override
    public void deleteFile(String fileUrl) {

    }
}
