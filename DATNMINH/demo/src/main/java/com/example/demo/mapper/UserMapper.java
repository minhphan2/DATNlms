package com.example.demo.mapper;

import org.springframework.stereotype.Component;
import com.example.demo.model.User;
import com.example.demo.DTO.UserResponse.UserResponse;
import com.example.demo.DTO.LoginResponse.LoginResponse;
import com.example.demo.DTO.UserRequest.*;
import com.example.demo.DTO.CourseResponse.CourseResponse;
import com.example.demo.model.Course;
import com.example.demo.model.Department;
import com.example.demo.Repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import com.example.demo.DTO.DepartmentResponse.DepartmentResponse;


@Component  

public class UserMapper {
    @Autowired
    private DepartmentRepository departmentRepository;
    
    public User toEntity(CreateRequest request){
         Department department = departmentRepository.findById(request.getDepartment()).orElse(null);
        return User.builder()
        .fullname(request.getFullname())
        .email(request.getEmail())
        .password(request.getPassword())
        .role(request.getRole())
        .phone(request.getPhone())
        .avatar(request.getAvatar())
        .status(request.getStatus())
        .department(department)
        .build();

    }


    public void updateEntity(User user, UpdateRequest request){

        user.setFullname(request.getFullname());
        user.setEmail(request.getEmail());



        if (request.getPassword() != null && !request.getPassword().isEmpty()){
            user.setPassword(request.getPassword());
        }

        user.setRole(request.getRole());
        user.setPhone(request.getPhone());
        user.setAvatar(request.getAvatar());
        user.setStatus(request.getStatus());
        Department department = departmentRepository.findById(request.getDepartment()).orElse(null);
        user.setDepartment(department);

    }

    public UserResponse toResponse(User user){
        DepartmentResponse departmentResponse = null;
        if( user.getDepartment() != null){
            departmentResponse = DepartmentResponse.builder()
            .id(user.getDepartment().getId())
            .name(user.getDepartment().getName())
            .build();
        }
        return  UserResponse.builder()
        .id(user.getId())
        .fullname(user.getFullname())
        .email(user.getEmail())
        .role(user.getRole())
        .phone(user.getPhone())
        .avatar(user.getAvatar())
        .status(user.getStatus())
        .department(departmentResponse)
        .build();
    }


    public LoginResponse toLoginResponse(User user , String token){
        return LoginResponse.builder()
        .token(token)
        .user(toResponse(user))
        .build();
    }

    public CourseResponse toCourseResponse(Course course){
        CourseResponse response = new CourseResponse();
        response.setId(course.getId());
        response.setTitle(course.getTitle());
        response.setDescription(course.getDescription());
        response.setTeacherName(course.getTeacher().getFullname());
        return response;
    }
}
