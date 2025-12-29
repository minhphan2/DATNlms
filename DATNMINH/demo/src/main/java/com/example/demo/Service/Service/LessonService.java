package com.example.demo.Service.Service;


import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.DTO.LessonResponse.*;
import com.example.demo.DTO.LessonRequest.CreateRequest;
import com.example.demo.DTO.LessonRequest.UpdateRequest;



@Service
public interface LessonService {
    List<LessonResponse> findAll();
    LessonResponse createLesson(CreateRequest request);
    LessonResponse updateLesson(Integer id , UpdateRequest request);
    List <LessonResponse> getLessonsBySectionId(Integer sectionId);
}
