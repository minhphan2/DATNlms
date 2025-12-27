package com.example.demo.mapper;


import org.springframework.stereotype.Component;
import com.example.demo.model.Lesson;
import com.example.demo.DTO.LessonResponse.*;
import com.example.demo.model.Section;
import com.example.demo.DTO.LessonRequest.*;

@Component
public class LessonMapper {

    public LessonResponse toResponse(Lesson lesson)
    {
        return LessonResponse.builder()
        .lessonId(lesson.getId())
        .sectionTitle(lesson.getSection().getTitle())
        .title(lesson.getTitle())
        .content(lesson.getContent())
        .position(lesson.getPosition())
        .videoUrl(lesson.getVideoUrl())
        .build();
    }

    public Lesson toEntity(CreateRequest request, Section section){
        return Lesson.builder()
        .section(section)
        .title(request.getTitle())
        .content(request.getContent())
        .position(request.getPosition())
        .videoUrl(request.getVideoUrl())
        .build();
    }

    public void updateEntity(Lesson lesson,UpdateRequest request, Section section){
        lesson.setSection(section);
        lesson.setTitle(request.getTitle());
        lesson.setPosition(request.getPosition());
        lesson.setVideoUrl(request.getVideoUrl());
    }
}
