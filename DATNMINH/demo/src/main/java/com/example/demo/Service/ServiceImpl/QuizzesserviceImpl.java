package com.example.demo.Service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.model.Course;
import com.example.demo.model.Quizzes;

import java.util.List;

import com.example.demo.DTO.QuizzesResponse.*;
import com.example.demo.DTO.QuizzesRequest.*;
import com.example.demo.Service.Service.QuizzesService;
import com.example.demo.Repository.QuizzesRepository;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.mapper.QuizzesMapper;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;


@Service
@RequiredArgsConstructor
public class QuizzesserviceImpl implements QuizzesService {

    private final QuizzesRepository quizzesRepository;
    private final QuizzesMapper quizzesMapper;
    private final CourseRepository courseRepository;


    @Override
    public List<QuizzesResponse> findAll() {
        return quizzesRepository.findAll().stream()
        .map(quizzesMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public QuizzesResponse createQuizzes(CreateRequest request) {
        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));

        Quizzes quizzes = quizzesMapper.toEntity(request, course);
        Quizzes savedQuizzes = quizzesRepository.save(quizzes);
        return quizzesMapper.toResponse(savedQuizzes);
    }

    @Override
    public QuizzesResponse updateQuizzes(Integer id, UpdateRequest request) {
        Quizzes quizzes = quizzesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Quizzes not found"));

        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));

        quizzesMapper.updateEntity(quizzes, request, course);
        Quizzes updatedQuizzes = quizzesRepository.save(quizzes);
        return quizzesMapper.toResponse(updatedQuizzes);
    }

    @Override
    public void deleteQuizzes(Integer id) {
        Quizzes quizzes = quizzesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Quizzes not found"));
        quizzesRepository.delete(quizzes);
    }

    @Override
    public QuizzesResponse findById(Integer id) {
        Quizzes quizzes = quizzesRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Quizzes not found"));
        return quizzesMapper.toResponse(quizzes);
    }
    
}
