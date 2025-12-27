package com.example.demo.DTO.EnrollmentRequest;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;


import jakarta.validation.constraints.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class UpdateRequest {
    @NotNull(message = "Course ID is required")
    private Integer courseId;

    @NotNull(message = "User ID is required")
    private Integer studentId;

}
