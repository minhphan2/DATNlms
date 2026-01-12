package com.example.demo.Service.Service;

import org.springframework.stereotype.Service;
import com.example.demo.model.Department;

import com.example.demo.DTO.DepartmentRequest.CreateRequest;
import com.example.demo.DTO.DepartmentResponse.DepartmentResponse;
import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.DTO.DepartmentRequest.UpdateRequest;

import java.util.List;
@Service
public interface DepartmentService {
    List<DepartmentResponse> findAll();
    DepartmentResponse createDepartment(CreateRequest request);
    DepartmentResponse updateDepartment(Integer id, UpdateRequest request);

}
