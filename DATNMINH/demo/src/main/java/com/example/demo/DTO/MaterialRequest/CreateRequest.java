package com.example.demo.DTO.MaterialRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


import jakarta.validation.constraints.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequest {
    @NotNull(message = "MaterialId name is required")   
    private Integer materialId;

    @NotNull(message = "LessonId is required")
    private Integer lessonId;

    @NotNull(message = "Filename is required")
    private String fileName;

    @NotNull(message = "FileUrl is required")
    private String fileUrl;

    @NotNull(message = "FileType is required")
    private String fileType;

    @NotNull(message = "Uploadedtime is required")
    private String uploadedTime;
}
