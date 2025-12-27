package com.example.demo.DTO.LessonResponse;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class LessonResponse {
    private Integer lessonId;
    private String sectionTitle;
    private String title;
    private String content;
    private String videoUrl;
    private Integer position;
    private Integer sectionId;
    private LocalDateTime createdAt;
}
