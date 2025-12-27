package com.example.demo.DTO.AnswerResponse;



import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AnswerResponse {
    private Integer answerId;
    private Integer questionId;
    private String answerText;
    private Boolean isCorrect;
}
