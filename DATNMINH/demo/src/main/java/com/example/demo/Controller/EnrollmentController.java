package com.example.demo.Controller;


import com.example.demo.Service.Service.CourseService;
import com.example.demo.DTO.CourseResponse.CourseResponse;

import com.example.demo.Repository.CourseRepository;
import com.example.demo.Service.ServiceImpl.CourseServiceImpl;
import com.example.demo.model.Course;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import com.example.demo.DTO.EnrollmentRequest.*;
import com.example.demo.Service.Service.EnrollmentService;
import com.example.demo.DTO.EnrollmentResponse.EnrollmentResponse;
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





@RestController
@RequestMapping("api/enrollments")
@RequiredArgsConstructor
public class EnrollmentController {
    private final EnrollmentService enrollmentService;

    @GetMapping
    public ResponseEntity<List<EnrollmentResponse>> getAllEnrollments(){
        return ResponseEntity.ok(enrollmentService.findAll());
    }

    @PostMapping
    public ResponseEntity<EnrollmentResponse> createEnrollment(@RequestBody CreateRequest request){
        EnrollmentResponse enrollmentResponse = enrollmentService.createEnrollment(request);
        return ResponseEntity.ok(enrollmentResponse);
    }

    
    
}
