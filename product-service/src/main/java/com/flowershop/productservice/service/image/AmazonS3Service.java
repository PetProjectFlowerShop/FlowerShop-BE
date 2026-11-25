package com.flowershop.productservice.service.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;

import java.io.IOException;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AmazonS3Service implements FileStorageService {
    private final S3Client s3Client;
    private final ImageConverterService imageConverterService;
    @Value("${aws.s3.bucket-name}")
    private String bucket;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        byte[] imageBytes = imageConverterService.convertToWebp(file);
        String key = (UUID.randomUUID()) + ".webp";
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType("image/webp")
            .acl("public-read")
            .build();
        s3Client.putObject(putObjectRequest,  RequestBody.fromBytes(imageBytes));
        return key;
    }

    @Override
    public void deleteFile(String fileUrl) {
        s3Client.deleteObject(builder -> builder
            .bucket(bucket)
            .key(fileUrl)
            .build()
        );


    }
}
