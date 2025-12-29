package com.example.demo.Controller;



import com.example.demo.Service.Service.CourseService;
import com.example.demo.DTO.CourseResponse.CourseResponse;

import com.example.demo.Repository.CourseRepository;
import com.example.demo.Service.ServiceImpl.CourseServiceImpl;
import com.example.demo.model.Course;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.demo.DTO.CourseRequest.CreateRequest;
import com.example.demo.DTO.CourseRequest.UpdateRequest;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import com.example.demo.model.User;




@RestController
@RequestMapping("api/courses")
@RequiredArgsConstructor
public class Coursecontroller {

    private final CourseService courseService;
    @GetMapping
    public ResponseEntity<List<CourseResponse>> getAllCourses(){
        return ResponseEntity.ok(courseService.findAll());
    }
    

    @PostMapping
    public ResponseEntity<CourseResponse> createCourse(@RequestBody CreateRequest request){
        CourseResponse courseResponse = courseService.createCourse(request);
        return ResponseEntity.ok(courseResponse);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<CourseResponse> updateCourse(@PathVariable Integer id, @RequestBody UpdateRequest request){ 
        CourseResponse courseResponse = courseService.updateCourse(id,request);
        return ResponseEntity.ok(courseResponse);
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseResponse> getCourseById(@PathVariable Integer id){
        CourseResponse courseResponse = courseService.getCourseById(id);
        return ResponseEntity.ok(courseResponse);
    }

    

}
