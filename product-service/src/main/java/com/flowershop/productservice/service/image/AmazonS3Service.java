package com.flowershop.productservice.service.image;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetUrlRequest;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.net.URI;
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

        return s3Client.utilities()
            .getUrl(GetUrlRequest.builder()
                .bucket(bucket)
                .key(key)
                .build())
            .toExternalForm();
    }

    @Override
    public void deleteFile(String fileUrl) {
        if (fileUrl == null || fileUrl.isBlank()) {
            return;
        }
        String key = extractKeyFromUrl(fileUrl);

        s3Client.deleteObject(builder -> builder
            .bucket(bucket)
            .key(key)
        );
    }

    private String extractKeyFromUrl(String fileUrl) {
        try {
            URI uri = new URI(fileUrl);
            String path = uri.getPath();

            if (path.startsWith("/")) {
                path = path.substring(1);
            }
            return path;
        } catch (Exception e) {
            return fileUrl;
        }
    }
}
