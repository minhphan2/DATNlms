package com.example.demo.Service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.Service.Service.AnswerService;
import com.example.demo.DTO.AnswerResponse.*;
import com.example.demo.DTO.AnswerRequest.*;
import java.util.List;
import com.example.demo.Repository.AnswerRepository;
import com.example.demo.Repository.QuestionRepository;
import com.example.demo.mapper.AnswerMapper;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AnswerserviceImpl implements AnswerService {
    private final AnswerRepository answerrepository;
    private final AnswerMapper answermapper;
    private final QuestionRepository questionRepository;


    @Override
    public List<AnswerResponse> findAll() {
        return answerrepository.findAll().stream()
        .map(answermapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public AnswerResponse createAnswer(CreateRequest request) {
        Question question = questionRepository.findById(request.getQuestionId())
        .orElseThrow(() -> new RuntimeException("Question not found"));
        Answer answer = answermapper.toEntity(request, question);
        Answer savedAnswer = answerrepository.save(answer);
        return answermapper.toResponse(savedAnswer);
    }

    @Override
    public AnswerResponse updateAnswer(Integer id, UpdateRequest request) {
        Answer answer = answerrepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Answer not found"));
        Question question = questionRepository.findById(request.getQuestionId())
        .orElseThrow(() -> new RuntimeException("Question not found"));
        answermapper.updateEntity(question, request, answer);
        Answer updatedAnswer = answerrepository.save(answer);
        return answermapper.toResponse(updatedAnswer);
    }

    @Override
    public void deleteAnswer(Integer id) {
        Answer answer = answerrepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Answer not found"));
        answerrepository.delete(answer);
    }


    @Override
    public AnswerResponse findById(Integer id) {
        Answer answer = answerrepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Answer not found"));
        return answermapper.toResponse(answer);
    }    
}
