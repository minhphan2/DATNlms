package com.example.demo.DTO.MaterialResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MaterialResponse {
    private Integer materialId;
    private String lessonId;
    private String lessonTitle;
    private String fileName;
    private String fileUrl;
    private String fileType;
    private LocalDateTime uploadedAt;
}
