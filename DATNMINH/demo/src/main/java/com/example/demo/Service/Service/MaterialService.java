package com.example.demo.Service.Service;
import java.util.List;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.DTO.MaterialRequest.CreateRequest;
import com.example.demo.DTO.MaterialRequest.UpdateRequest;
import com.example.demo.DTO.MaterialResponse.MaterialResponse;


@Service
public interface MaterialService {
    List<MaterialResponse> findAll();
    MaterialResponse createMaterial(CreateRequest request);
    MaterialResponse updateMaterial(Integer id, UpdateRequest request);
    void deleteMaterial(Integer id);

    MaterialResponse findById(Integer id);
    MaterialResponse uploadMaterial(Integer lessonId, MultipartFile file);
    List<MaterialResponse> uploadMultipleMaterials(Integer lessonId, List<MultipartFile> files);
    
    byte[] downloadMaterial(Integer id);
} 
