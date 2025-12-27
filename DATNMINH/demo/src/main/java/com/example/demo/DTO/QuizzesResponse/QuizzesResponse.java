package com.example.demo.DTO.QuizzesResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuizzesResponse {
    private Integer quizId;
    private Integer courseId;
    private String title;
    private Integer timeLimit; // in minutes
    private LocalDateTime createdAt;
}
