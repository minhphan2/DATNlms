package com.example.demo.DTO.CourseResponse;

import com.example.demo.model.Course;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CourseResponse {
    private Integer id;
    private String courseName;
    private String title;
    private String teacherName;
    private String description;
    private String category;
    private String thumbnail;
    private Integer maxStudents;
    private Course.Status status;
    private LocalDateTime createdAt;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}