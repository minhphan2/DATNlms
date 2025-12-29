package com.example.demo.Service.ServiceImpl;
import com.example.demo.Service.Service.MaterialService;

import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.MaterialRequest.CreateRequest;
import com.example.demo.DTO.MaterialRequest.UpdateRequest;
import com.example.demo.DTO.MaterialResponse.MaterialResponse;
import com.example.demo.Repository.MaterialRepository;
import com.example.demo.mapper.MaterialMapper;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import com.example.demo.Repository.LessonRepository;
import com.example.demo.model.Lesson;
import com.example.demo.model.Material;
import com.example.demo.Service.Service.FileStorageService;


@Service
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
    private final MaterialRepository materialRepository;
    private final LessonRepository lessonRepository;
    private final MaterialMapper materialMapper;
    private final FileStorageService fileStorageService;

    
    
    @Override
    public List<MaterialResponse> findAll() {
        return materialRepository.findAll().stream()
        .map(materialMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public MaterialResponse createMaterial(CreateRequest request){
        Lesson lesson = lessonRepository.findById(request.getLessonId())
        .orElseThrow(() -> new RuntimeException("Lesson not found"));

        Material material = materialMapper.toEntity(request, lesson);
        Material savedMaterial = materialRepository.save(material);
        return materialMapper.toResponse(savedMaterial);
    }


    @Override
    public MaterialResponse updateMaterial(Integer id, UpdateRequest request){
        Material material = materialRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay tai lieu"));

        Lesson lesson = lessonRepository.findById(request.getLessonId())
        .orElseThrow(() -> new RuntimeException("khong tim thay bai hoc"));
        materialMapper.updateEntity(material, request, lesson);
        Material updatedMaterial = materialRepository.save(material);
        return materialMapper.toResponse(updatedMaterial);
    }


    @Override
    public void deleteMaterial(Integer id){
        Material material = materialRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay tai lieu"));
        materialRepository.delete(material);
    }


    @Override
    public MaterialResponse findById(Integer id){
        Material material = materialRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay tai lieu"));
        return materialMapper.toResponse(material);
    }

    @Override
    public MaterialResponse uploadMaterial(Integer lessonId, MultipartFile file) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay bai hoc"));
        
        String fileUrl = fileStorageService.storeFile(file, "materials");
        
        String fileType = file.getContentType();
        if (fileType == null || fileType.isEmpty()) {
        
            String filename = file.getOriginalFilename();
            String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
            fileType = getFileType(extension);
        }

        Material material = Material.builder()
                .lesson(lesson)
                .fileName(file.getOriginalFilename())
                .fileUrl(fileUrl)
                .fileType(fileType)
                .build();
        
        Material savedMaterial = materialRepository.save(material);
        return materialMapper.toResponse(savedMaterial);
    }


     @Override
    public List<MaterialResponse> uploadMultipleMaterials(Integer lessonId, List<MultipartFile> files) {
        Lesson lesson = lessonRepository.findById(lessonId)
                .orElseThrow(() -> new RuntimeException("Khong tim thay bai hoc"));
        
        List<MaterialResponse> responses = new ArrayList<>();
        
        for (MultipartFile file : files) {
            if (!file.isEmpty()) {
                String fileUrl = fileStorageService.storeFile(file, "materials");
                
                String fileType = file.getContentType();
            if (fileType == null || fileType.isEmpty()) {
                String filename = file.getOriginalFilename();
                String extension = filename.substring(filename.lastIndexOf(".") + 1).toLowerCase();
                fileType = getFileType(extension);
            }

                Material material = Material.builder()
                        .lesson(lesson)
                        .fileName(file.getOriginalFilename())
                        .fileUrl(fileUrl)
                        .build();
                
                Material savedMaterial = materialRepository.save(material);
                responses.add(materialMapper.toResponse(savedMaterial));
            }
        }
        
        return responses;
    }
    

    @Override
    public byte[] downloadMaterial(Integer id) {
        Material material = materialRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Khong tim thay tai lieu"));
        
        return fileStorageService.loadFile(material.getFileUrl());
    }


    private String getFileType(String extension) {
        return switch (extension) {
            case "pdf" -> "application/pdf";
            case "doc", "docx" -> "application/msword";
            case "xls", "xlsx" -> "application/vnd.ms-excel";
            case "ppt", "pptx" -> "application/vnd.ms-powerpoint";
            case "zip" -> "application/zip";
            case "jpg", "jpeg" -> "image/jpeg";
            case "png" -> "image/png";
            case "txt" -> "text/plain";
            default -> "application/octet-stream";
        };
    }

    @Override
    public List<MaterialResponse> getMaterialsByLessonId(Integer lessonId) {
        List<Material> materials = materialRepository.findByLessonId(lessonId);
        return materials.stream()
                .map(materialMapper::toResponse)
                .collect(Collectors.toList());
    }
}
