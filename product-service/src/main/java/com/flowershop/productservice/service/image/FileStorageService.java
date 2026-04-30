package com.flowershop.productservice.service.image;

import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;

/**
 * Service interface for managing file operations with cloud storage.
 */
public interface FileStorageService {

    /**
     * Uploads a file to the storage provider and returns its public URL.
     *
     * @param file the multipart file to be uploaded.
     * @return the public URL of the uploaded file.
     * @throws IOException if an error occurs during file processing or upload.
     */
    String uploadFile(MultipartFile file) throws IOException;

    /**
     * Deletes a file from the storage provider using its public URL or unique key.
     *
     * @param fileUrl the full public URL or the storage key of the file to be deleted.
     */
    void deleteFile(String fileUrl);
}
