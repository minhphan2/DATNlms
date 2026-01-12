package com.example.demo.Controller;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import com.example.demo.Service.Service.DepartmentService;
import com.example.demo.DTO.DepartmentRequest.CreateRequest;
import com.example.demo.DTO.DepartmentRequest.UpdateRequest;
import com.example.demo.DTO.DepartmentResponse.DepartmentResponse;
import java.util.List;

@RestController
@RequestMapping("api/departments")
@RequiredArgsConstructor
public class DepartmentController {
    private final DepartmentService departmentService;

    @GetMapping
    public ResponseEntity<List<DepartmentResponse>> getAllDepartments(){
        return ResponseEntity.ok(departmentService.findAll());
    }
}
