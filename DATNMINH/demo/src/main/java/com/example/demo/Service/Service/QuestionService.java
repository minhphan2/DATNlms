package com.example.demo.Service.Service;

import org.springframework.stereotype.Service;


import java.util.List;

import com.example.demo.DTO.QuestionResponse.*;
import com.example.demo.DTO.QuestionRequest.*;

@Service
public interface QuestionService {

    List<QuestionResponse> findAll();
    QuestionResponse createQuestion(CreateRequest request);
    QuestionResponse updateQuestion(Integer id, UpdateRequest request);
    void deleteQuestion(Integer id);

    QuestionResponse findById(Integer id);
    
} 
