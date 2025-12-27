package com.example.demo.Controller.auth;

import lombok.RequiredArgsConstructor;

import java.util.List;
import com.example.demo.Service.Service.UserService;
import com.example.demo.Service.Service.auth.LoginService;
import com.example.demo.DTO.UserResponse.UserResponse;
import com.example.demo.DTO.LoginResponse.LoginResponse;
import com.example.demo.DTO.UserRequest.CreateRequest;
import com.example.demo.DTO.UserRequest.UpdateRequest;

import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.ServiceImpl.UserServiceImpl;
import com.example.demo.model.User;
import com.example.demo.DTO.UserRequest.LoginRequest;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.servlet.http.HttpServletRequest;


@RestController
@RequestMapping("api/users")
@RequiredArgsConstructor
public class Logincontroller {
 private final LoginService loginService;

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest request)
    {
        try {
            LoginResponse response = loginService.login(request);
            return ResponseEntity.ok(response);
        } catch (RuntimeException e) {
            LoginResponse errorResponse = LoginResponse.builder()
                    .build();
            return ResponseEntity.badRequest().body(errorResponse);
        }
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
       return ResponseEntity.ok().build();
    }

    
}
