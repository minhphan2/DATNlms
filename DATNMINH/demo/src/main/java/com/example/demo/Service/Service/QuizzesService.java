package com.example.demo.Service.Service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Quizzes;

import java.util.List;

import com.example.demo.DTO.QuizzesResponse.*;
import com.example.demo.DTO.QuizzesRequest.*;

@Service
public interface QuizzesService {
    List<QuizzesResponse> findAll();
    QuizzesResponse createQuizzes(CreateRequest request);
    QuizzesResponse updateQuizzes(Integer id, UpdateRequest request);
    void deleteQuizzes(Integer id);

    QuizzesResponse findById(Integer id);
    
}
