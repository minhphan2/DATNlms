package com.example.demo.mapper;


import org.springframework.stereotype.Component;
import com.example.demo.model.Material;
import com.example.demo.DTO.MaterialResponse.*;
import com.example.demo.model.Lesson;
import com.example.demo.DTO.MaterialRequest.*;

@Component
public class MaterialMapper {
    public MaterialResponse toResponse(Material material)
    {
        return MaterialResponse.builder()
        .materialId(material.getId())
        .lessonTitle(material.getLesson().getTitle())
        .fileName(material.getFileName())
        .fileType(material.getFileType())
        .fileUrl(material.getFileUrl())
        .build();
    }

    public Material toEntity(CreateRequest request, Lesson lesson){
        return Material.builder()
        .lesson(lesson)
        .fileName(request.getFileName())
        .fileType(request.getFileType())
        .fileUrl(request.getFileUrl())
        .build();
    }

    public void updateEntity(Material material, UpdateRequest request, Lesson lesson){
        material.setLesson(lesson);
        material.setFileName(request.getFileName());
        material.setFileType(request.getFileType());
        material.setFileUrl(request.getFileUrl());
    }
}
