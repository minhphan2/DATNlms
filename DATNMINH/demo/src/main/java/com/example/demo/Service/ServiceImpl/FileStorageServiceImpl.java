package com.example.demo.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.demo.Service.Service.FileStorageService;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.UUID;

@Service
public class FileStorageServiceImpl implements FileStorageService {
    
    private final Path rootLocation = Paths.get("uploads");

    public FileStorageServiceImpl() {
        try {
            Files.createDirectories(rootLocation);
        } catch (IOException e) {
            throw new RuntimeException("Khong the tao thu muc uploads", e);
        }
    }

    @Override
    public String storeFile(MultipartFile file, String folder) {
        try {
            if (file.isEmpty()) {
                throw new RuntimeException("File rong");
            }

            // Tạo tên file unique: uuid_filename.pdf
            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));
            String uniqueFilename = UUID.randomUUID().toString() + extension;

            // Tạo thư mục con (materials, assignments...)
            Path folderPath = rootLocation.resolve(folder);
            Files.createDirectories(folderPath);

            // Lưu file
            Path destinationFile = folderPath.resolve(uniqueFilename);
            Files.copy(file.getInputStream(), destinationFile, StandardCopyOption.REPLACE_EXISTING);

            // Trả về đường dẫn: /materials/abc123.pdf
            return "/" + folder + "/" + uniqueFilename;

        } catch (IOException e) {
            throw new RuntimeException("Loi khi luu file", e);
        }
    }

    @Override
    public String storeFile2(MultipartFile file, String folder) {
    String originalFileName = file.getOriginalFilename();
    String uuid = UUID.randomUUID().toString();
    String fileName = uuid + "-" + originalFileName;
    Path dirPath = Paths.get("uploads", folder); // Thư mục uploads/folder
    try {
        Files.createDirectories(dirPath); // Tạo thư mục nếu chưa có
        Path filePath = dirPath.resolve(fileName);
        file.transferTo(filePath.toFile()); // Lưu file
        return "/" + folder + "/" + fileName; // Đường dẫn lưu trong DB
    } catch (IOException e) {
        throw new RuntimeException("Lỗi lưu file", e);
    }
}

    @Override
    public byte[] loadFile(String filePath) {
        try {
            Path file = rootLocation.resolve(filePath.substring(1)); // Bỏ dấu / đầu
            return Files.readAllBytes(file);
        } catch (IOException e) {
            throw new RuntimeException("Khong tim thay file", e);
        }
    }

    @Override
    public void deleteFile(String filePath) {
        try {
            Path file = rootLocation.resolve(filePath.substring(1));
            Files.deleteIfExists(file);
        } catch (IOException e) {
            throw new RuntimeException("Loi khi xoa file", e);
        }
    }
}