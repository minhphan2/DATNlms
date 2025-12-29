package com.example.demo.Service.ServiceImpl;

import java.util.List;
import org.springframework.stereotype.Service;


import com.example.demo.model.Course;
import com.example.demo.DTO.SectionRequest.UpdateRequest;
import com.example.demo.DTO.SectionRequest.CreateRequest;
import com.example.demo.model.Section;
import com.example.demo.Repository.SectionRepository;
import com.example.demo.DTO.SectionResponse.SectionResponse;
import com.example.demo.Service.Service.SectionService;
import com.example.demo.mapper.SectionMapper;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import com.example.demo.Repository.CourseRepository;


@Service
@RequiredArgsConstructor
public class SectionServiceImpl implements SectionService {
    private final SectionRepository sectionRepository;
    private final SectionMapper sectionMapper;
    private final CourseRepository courseRepository;
    

    @Override
    public List<SectionResponse> countByCourseIdList(Integer Id){
        return sectionRepository.findByCourseId(Id).stream()
        .map(sectionMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public SectionResponse createSection(CreateRequest request){
        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));
        
        Section section = sectionMapper.toEntity(request, course);
        Section savedSection = sectionRepository.save(section);
        return sectionMapper.toResponse(savedSection);
    }

    @Override
    public SectionResponse updateSection(Integer id, UpdateRequest request){
        Section section = sectionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("Section not found"));

        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("Course not found"));

        sectionMapper.updateEntity(section, request, course);
        Section updatedSection = sectionRepository.save(section);
        return sectionMapper.toResponse(updatedSection);
    }


    @Override
    public List<SectionResponse> findbyCourseId (Integer id){
        List<Section> sections = sectionRepository.findByCourseId(id);
        List<SectionResponse> sectionResponses = sections.stream()
                .map(sectionMapper::toResponse)
                .collect(Collectors.toList());
        return sectionResponses;
    }

}
