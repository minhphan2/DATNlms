package com.example.demo.Service.Service.auth;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.DTO.UserRequest.LoginRequest;
import com.example.demo.DTO.LoginResponse.LoginResponse;
import com.example.demo.DTO.UserResponse.UserResponse;

import com.example.demo.DTO.UserRequest.CreateRequest;
import com.example.demo.DTO.UserRequest.UpdateRequest;
import com.example.demo.model.User;


@Service
public interface LoginService {
     public LoginResponse login(LoginRequest request);

     void logout(String token);
}
