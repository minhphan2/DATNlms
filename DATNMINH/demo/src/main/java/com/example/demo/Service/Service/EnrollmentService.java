package com.example.demo.Service.Service;

import java.util.List;

import org.springframework.stereotype.Service;


import com.example.demo.DTO.EnrollmentRequest.CreateRequest;
import com.example.demo.DTO.EnrollmentResponse.EnrollmentResponse;

@Service
public interface EnrollmentService {
    List<EnrollmentResponse>findAll();
    EnrollmentResponse createEnrollment(CreateRequest request);
    
}
