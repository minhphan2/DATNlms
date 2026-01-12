package com.example.demo.mapper;

import org.springframework.stereotype.Component;
import com.example.demo.model.Department;
import com.example.demo.DTO.DepartmentRequest.CreateRequest;
import com.example.demo.DTO.DepartmentRequest.UpdateRequest;
import com.example.demo.DTO.DepartmentResponse.DepartmentResponse;

@Component
public class DepartmentMapper {
    public DepartmentResponse toResponse(Department department) {
        return DepartmentResponse.builder()
                .id(department.getId())
                .name(department.getName())
                .build();
    }

    public Department toEntity(CreateRequest request) {
        return Department.builder()
                .name(request.getName())
                .build();
    }

    public void updateEntity(Department department, UpdateRequest request) {
        department.setName(request.getName());
    }


}
