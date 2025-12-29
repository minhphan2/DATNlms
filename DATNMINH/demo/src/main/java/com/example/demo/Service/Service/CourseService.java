package com.example.demo.Service.Service;

import java.util.List;

import org.springframework.stereotype.Service;
import com.example.demo.DTO.CourseResponse.CourseResponse;
import com.example.demo.DTO.CourseRequest.CreateRequest;
import com.example.demo.DTO.CourseRequest.UpdateRequest;


@Service
public interface CourseService {

    List<CourseResponse>findAll();   
    CourseResponse createCourse(CreateRequest request); 

    CourseResponse updateCourse(Integer id, UpdateRequest request);

    CourseResponse getCourseById(Integer id);
}
