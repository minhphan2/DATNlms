package com.example.demo.DTO.AnswerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequest {
    private Integer questionId;
    private String answerText;
    private Boolean isCorrect;
}