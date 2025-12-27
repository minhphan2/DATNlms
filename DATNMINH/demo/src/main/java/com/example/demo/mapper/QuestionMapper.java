package com.example.demo.mapper;

import org.springframework.stereotype.Component;
import com.example.demo.model.Question;
import com.example.demo.DTO.QuestionResponse.*;
import com.example.demo.DTO.QuestionRequest.*;
import com.example.demo.model.Quizzes;

@Component
public class QuestionMapper {
    public QuestionResponse toResponse(Question question)
    {
        return QuestionResponse.builder()
        .questionId(question.getQuestionid())
        .quizId(question.getQuiz().getQuizid())
        .questionText(question.getQuestionText())
        .type(question.getType())
        .build();
    }

    public Question toEntity(CreateRequest request, Quizzes quiz){
        return Question.builder()
        .quiz(quiz)
        .questionText(request.getQuestionText())
        .type(Question.QuestionType.valueOf(request.getType()))
        .build();
    }

    public void updateEntity(Question question, UpdateRequest request, Quizzes quiz){
        question.setQuiz(quiz);
        question.setQuestionText(request.getQuestionText());
        question.setType(Question.QuestionType.valueOf(request.getType()));
    }
}
