package com.example.demo.DTO.QuestionResponse;

import com.example.demo.model.Question;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class QuestionResponse {
    private Integer questionId;
    private Integer quizId;
    private String questionText;
    private Question.QuestionType type;
}
