package com.example.demo.DTO.QuestionRequest;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequest {
    private Integer quizId;
    private String questionText;
    private String type; 
}
