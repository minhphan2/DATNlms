package com.example.demo.DTO.CourseRequest;

import java.time.LocalDateTime;

import com.example.demo.model.Course;

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
    
    @NotBlank(message = "Title is required")
    private String title;

    @NotNull(message = "Teacher ID không được để trống")
    private Integer teacherId;

    @NotBlank(message = "Description is required")
    private String description;

    @NotNull(message = "Duration is required")
    @Min(value = 1, message = "Duration must be at least 1 hour")
    private Integer duration; 

    private String courseName;

     @Min(value = 1, message = "Số lượng học viên tối thiểu là 1")
    @Max(value = 500, message = "Số lượng học viên tối đa là 500")
    private Integer maxStudents;

    @NotNull(message = "Status is required")
    private Course.Status status;

    @NotBlank(message = "Category is required")
    private String category;

     @NotBlank(message = "startDate is required")
    private LocalDateTime startDate;

    @NotBlank(message = "endDate is required")
    private LocalDateTime endDate;
}
