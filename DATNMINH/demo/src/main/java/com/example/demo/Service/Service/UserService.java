package com.example.demo.Service.Service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserRequest.LoginRequest;
import com.example.demo.DTO.UserResponse.LoginResponse;
import com.example.demo.DTO.UserResponse.UserResponse;
import com.example.demo.DTO.CourseResponse.CourseResponse;

import com.example.demo.DTO.UserRequest.CreateRequest;
import com.example.demo.DTO.UserRequest.UpdateRequest;
import com.example.demo.model.User;

@Service
public interface UserService {
    
    List<UserResponse>findAll();

    User findById(Integer id);

    List<UserResponse>FindByRole(User.Role role);

    List<UserResponse>findByFullName(String fullname);

    User findByEmail(String email);

    User saveUser(User user);

    UserResponse updateUser(Integer id , UpdateRequest request);
    void deleteUser(Integer id);
    UserResponse createUser(CreateRequest request);

    UserResponse getMe(User user);

    List<CourseResponse> getMyCourses(User user);

    List<UserResponse> getStudentsByCourseId(Integer courseId);

    UserResponse getStudentById(Integer id);
}

