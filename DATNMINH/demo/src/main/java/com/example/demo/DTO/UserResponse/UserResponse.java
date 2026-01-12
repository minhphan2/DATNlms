package com.example.demo.DTO.UserResponse;

import com.example.demo.model.User;
import com.example.demo.DTO.DepartmentResponse.DepartmentResponse;
import com.example.demo.model.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UserResponse {
    private Integer id;
    private String fullname;
    private String email;
    private User.Role role;
    private String phone;
    private String avatar;
    private User.Status status;
    private DepartmentResponse department;
}