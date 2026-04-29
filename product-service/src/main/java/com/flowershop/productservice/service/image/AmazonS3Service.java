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
    @Value("${aws.s3.bucket-name}")
    private String bucket;

    @Override
    public String uploadFile(MultipartFile file) throws IOException {
        String[] parts = file.getOriginalFilename().split(".");
        String key = String.valueOf(UUID.randomUUID()) + "." + parts[parts.length - 1];
        PutObjectRequest putObjectRequest = PutObjectRequest.builder()
            .bucket(bucket)
            .key(key)
            .contentType(file.getContentType())
            .acl("public-read")
            .build();
        s3Client.putObject(putObjectRequest, RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
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
