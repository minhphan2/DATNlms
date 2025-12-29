package com.example.demo.Service.ServiceImpl;

import com.example.demo.Service.Service.LessonService;


import org.springframework.stereotype.Service;

import com.example.demo.Repository.SectionRepository;
import com.example.demo.Repository.LessonRepository;
import com.example.demo.DTO.LessonRequest.*;
import com.example.demo.mapper.LessonMapper;

import com.example.demo.model.Lesson;
import com.example.demo.model.Section;

import com.example.demo.DTO.LessonResponse.*;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class LessonServiceImpl implements LessonService {
    
    public final LessonRepository lessonRepository;
    public final LessonMapper lessonMapper;
    public final SectionRepository sectionRepository;

    @Override
    public List<LessonResponse> findAll(){
        return lessonRepository.findAll().stream()
        .map(lessonMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public LessonResponse createLesson(CreateRequest request){
        
        Section section = sectionRepository.findById(request.getSectionId())
        .orElseThrow(() -> new RuntimeException("Course not found"));
        
        Lesson lesson = lessonMapper.toEntity(request, section);
        Lesson savedLesson = lessonRepository.save(lesson);
        return lessonMapper.toResponse(savedLesson);
    }

    @Override
    public LessonResponse updateLesson(Integer id, UpdateRequest request){
        Lesson lesson = lessonRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay lesson"));

        Section section = sectionRepository.findById(request.getSectionId())
        .orElseThrow(() -> new RuntimeException("khong tim thay course"));

        lessonMapper.updateEntity(lesson, request, section);
        Lesson updatedLesson = lessonRepository.save(lesson);
        return lessonMapper.toResponse(updatedLesson);
    }

    @Override
    public List<LessonResponse> getLessonsBySectionId(Integer sectionId){
        List<Lesson> lessons = lessonRepository.findBySectionId(sectionId);
        return lessons.stream()
        .map(lessonMapper::toResponse)
        .collect(Collectors.toList());
    }
}
