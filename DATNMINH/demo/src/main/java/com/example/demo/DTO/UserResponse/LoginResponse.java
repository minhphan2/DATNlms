package com.example.demo.DTO.UserResponse;

import com.example.demo.model.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {
    private Integer id;
    private String fullname;
    private String email;
    private User.Role role;
    private String phone;
    private String avatar;
}
