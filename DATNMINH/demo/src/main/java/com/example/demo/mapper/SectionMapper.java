package com.example.demo.mapper;


import org.springframework.stereotype.Component;
import com.example.demo.model.Section;
import com.example.demo.DTO.SectionResponse.SectionResponse;
import com.example.demo.model.Course;
import com.example.demo.DTO.SectionRequest.*;;

@Component
public class SectionMapper {
    public SectionResponse toResponse(Section section)
    {
        return SectionResponse.builder()
        .id(section.getId())
        .courseName(section.getCourse().getCourseName())
        .title(section.getTitle())
        .position(section.getPosition())
        .startDate(section.getStartDate())
        .endDate(section.getEndDate())
        .build();
    }

    public Section toEntity(CreateRequest request, Course course){
        return Section.builder()
        .course(course)
        .title(request.getTitle())
        .position(request.getPosition())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .build();
    }

    public void updateEntity(Section section,UpdateRequest request, Course course){
        section.setCourse(course);
        section.setTitle(request.getTitle());
        section.setPosition(request.getPosition());
        section.setStartDate(request.getStartDate());
        section.setEndDate(request.getEndDate());
    }
}
