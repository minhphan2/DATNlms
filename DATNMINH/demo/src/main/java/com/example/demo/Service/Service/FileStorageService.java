package com.example.demo.Service.Service;

import org.springframework.web.multipart.MultipartFile;

public interface FileStorageService {
    String storeFile(MultipartFile file, String folder);
    byte[] loadFile(String filePath);
    void deleteFile(String filePath);
    
}