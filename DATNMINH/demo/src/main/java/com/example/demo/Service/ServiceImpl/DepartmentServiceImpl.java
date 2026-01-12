package com.example.demo.Service.ServiceImpl;
import org.springframework.stereotype.Service;

import com.example.demo.Repository.DepartmentRepository;
import com.example.demo.Service.Service.DepartmentService;
import com.example.demo.mapper.DepartmentMapper;
import com.example.demo.DTO.DepartmentRequest.CreateRequest;
import com.example.demo.DTO.DepartmentRequest.UpdateRequest;
import com.example.demo.DTO.DepartmentResponse.DepartmentResponse;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DepartmentServiceImpl implements DepartmentService {
    private final DepartmentRepository departmentRepository;
    private final DepartmentMapper departmentMapper;

    @Override
    public List<DepartmentResponse> findAll() {
        return departmentRepository.findAll()
                .stream()
                .map(departmentMapper::toResponse)
                .collect(Collectors.toList());
    }

    @Override
    public DepartmentResponse createDepartment(CreateRequest request) {
        var department = departmentMapper.toEntity(request);
        var savedDepartment = departmentRepository.save(department);
        return  departmentMapper.toResponse(savedDepartment);
    }

    @Override
    public DepartmentResponse updateDepartment(Integer id, UpdateRequest request) {
        var department = departmentRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Department not found"));
        departmentMapper.updateEntity(department, request);
        var updatedDepartment = departmentRepository.save(department);
        return departmentMapper.toResponse(updatedDepartment);
    }
    
}
