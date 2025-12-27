package com.example.demo.Service.Service;

import org.springframework.stereotype.Service;

import java.util.List;

import com.example.demo.DTO.AnswerResponse.*;
import com.example.demo.DTO.AnswerRequest.*;

@Service
public interface AnswerService {

    List<AnswerResponse> findAll();
    AnswerResponse createAnswer(CreateRequest request);
    AnswerResponse updateAnswer(Integer id, UpdateRequest request);
    void deleteAnswer(Integer id);

    AnswerResponse findById(Integer id);
    
}
