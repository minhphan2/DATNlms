package com.example.demo.Service.ServiceImpl;


import org.springframework.stereotype.Service;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Service.Service.CourseService;
import com.example.demo.mapper.CourseMapper;
import com.example.demo.Repository.UserRepository;
import com.example.demo.DTO.CourseRequest.CreateRequest;
import com.example.demo.DTO.CourseRequest.UpdateRequest;
import com.example.demo.model.User;
import com.example.demo.DTO.CourseResponse.CourseResponse;

import com.example.demo.model.Course;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
    
    private final CourseRepository courseRepository;
    private final CourseMapper courseMapper;
    private final UserRepository userRepository;


    @Override
    public List<CourseResponse> findAll() {
        return courseRepository.findAll().stream()
        .map(courseMapper::toResponse)
        .collect(Collectors.toList());
    }


    @Override
    public CourseResponse createCourse(CreateRequest request){

        User teacher = userRepository.findById(request.getTeacherId())
        .orElseThrow(()-> new RuntimeException("ko tim thay giao vien"));

        if(teacher.getRole() !=User.Role.teacher){
            throw new RuntimeException("Nguoi dung khong phai giao vien");
        }

        Course course = courseMapper.toEntity(request, teacher);
        Course savedCourse = courseRepository.save(course);
        return courseMapper.toResponse(savedCourse);
    }

    @Override
    public CourseResponse updateCourse(Integer id, UpdateRequest request){
        Course existingCourse = courseRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Ko tim thay khoa hoc"));

        User teacher = userRepository.findById(request.getTeacherId())
        .orElseThrow(()-> new RuntimeException("khong tim thay nguoi dung"));

        if(teacher.getRole() != User.Role.teacher){
            throw new RuntimeException("nguoidung khong phai giao vien");
        }

        courseMapper.updateEntity(existingCourse, request, teacher);
        Course updatedCourse = courseRepository.save(existingCourse);
        return courseMapper.toResponse(updatedCourse);
    }

    @Override
    public CourseResponse getCourseById(Integer id){
        Course course = courseRepository.findById(id)
        .orElseThrow(()-> new RuntimeException("Ko tim thay khoa hoc"));
        return courseMapper.toResponse(course);
    }
}
