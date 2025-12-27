package com.example.demo.mapper;

import org.springframework.stereotype.Component;
import com.example.demo.model.Quizzes;
import com.example.demo.DTO.QuizzesResponse.*;
import com.example.demo.DTO.QuizzesRequest.*;
import com.example.demo.model.Course;

@Component
public class QuizzesMapper {
    public QuizzesResponse toResponse(Quizzes quizzes)
    {
        return QuizzesResponse.builder()
        .quizId(quizzes.getQuizid())
        .courseId(quizzes.getCourse().getId())
        .title(quizzes.getTitle())
        .timeLimit(quizzes.getTimeLimit())
        .createdAt(quizzes.getCreatedAt())
        .build();
    }

    public Quizzes toEntity(CreateRequest request, Course course){
        return Quizzes.builder()
        .course(course)
        .title(request.getTitle())
        .timeLimit(request.getTimeLimit())
        .build();
    }

    public void updateEntity(Quizzes quizzes, UpdateRequest request, Course course){
        quizzes.setCourse(course);
        quizzes.setTitle(request.getTitle());
        quizzes.setTimeLimit(request.getTimeLimit());
    }
}
