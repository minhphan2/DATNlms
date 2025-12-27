package com.example.demo.Service.ServiceImpl;


import org.springframework.stereotype.Service;

import com.example.demo.model.Question;
import com.example.demo.model.Quizzes;

import java.util.List;

import com.example.demo.DTO.QuestionResponse.*;
import com.example.demo.DTO.QuestionRequest.*;
import com.example.demo.Service.Service.QuestionService;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.Repository.QuizzesRepository;
import com.example.demo.mapper.QuestionMapper;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuestionServiceImpl  implements QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionMapper questionMapper;
    private final QuizzesRepository quizzesRepository;


    @Override
    public List<QuestionResponse> findAll() {
        return questionRepository.findAll().stream()
        .map(questionMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public QuestionResponse createQuestion(CreateRequest request) {
        Quizzes quizzes = quizzesRepository.findById(request.getQuizId())
        .orElseThrow(() -> new RuntimeException("Quizzes not found"));
        Question question = questionMapper.toEntity(request, quizzes);
        Question savedQuestion = questionRepository.save(question);
        return questionMapper.toResponse(savedQuestion);
    }

    @Override
    public QuestionResponse updateQuestion(Integer id, UpdateRequest request) {
        Question question = questionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Question not found"));

        Quizzes quizzes = quizzesRepository.findById(request.getQuizId())
        .orElseThrow(() -> new RuntimeException("Quizzes not found"));
        questionMapper.updateEntity(question, request, quizzes);
        Question updatedQuestion = questionRepository.save(question);
        return questionMapper.toResponse(updatedQuestion);
    }

    @Override
    public void deleteQuestion(Integer id) {
        Question question = questionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Question not found"));
        questionRepository.delete(question);
    }

    @Override
    public QuestionResponse findById(Integer id) {
        Question question = questionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Question not found"));
        return questionMapper.toResponse(question);
    }
    
}
