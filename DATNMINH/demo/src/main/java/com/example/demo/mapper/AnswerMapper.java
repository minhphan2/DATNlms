package com.example.demo.mapper;

import org.springframework.stereotype.Component;
import com.example.demo.DTO.AnswerRequest.CreateRequest;
import com.example.demo.DTO.AnswerRequest.UpdateRequest;
import com.example.demo.DTO.AnswerResponse.AnswerResponse;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;


@Component
public class AnswerMapper {

    public AnswerResponse toResponse(Answer answer)
    {
        return AnswerResponse.builder()
        .answerId(answer.getAnswerid())
        .questionId(answer.getQuestion().getQuestionid())
        .answerText(answer.getAnswerText())
        .isCorrect(answer.getIsCorrect())
        .build();
    }

    public Answer toEntity(CreateRequest createRequest, Question question){
        return Answer.builder()
        .question(question)
        .answerText(createRequest.getAnswerText())
        .isCorrect(createRequest.getIsCorrect())
        .build();
    }


    public void updateEntity(Question question, UpdateRequest updateRequest, Answer answer) {
        
        answer.setQuestion(question);
        answer.setAnswerText(updateRequest.getAnswerText());
        answer.setIsCorrect(updateRequest.getIsCorrect());
    }
}