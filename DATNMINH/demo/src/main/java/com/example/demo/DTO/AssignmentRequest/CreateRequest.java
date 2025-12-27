package com.example.demo.DTO.AssignmentRequest;

import com.example.demo.model.Assignment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequest {
    private Integer assignmentId;
    private Integer courseId;
    private String title;
    private String instructions;
    private Integer maxScore;
    private LocalDateTime deadline;
    private LocalDateTime createdAt; 
}
