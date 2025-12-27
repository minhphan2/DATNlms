package com.example.demo.DTO.EnrollmentResponse;


import com.example.demo.model.Enrollment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class EnrollmentResponse {
    private Integer id;
    private String courseName;
    private String userFullName;
    private LocalDateTime enrollmentDate;
}
