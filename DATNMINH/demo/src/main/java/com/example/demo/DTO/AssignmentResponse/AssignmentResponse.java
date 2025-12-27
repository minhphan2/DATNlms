package com.example.demo.DTO.AssignmentResponse;



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
public class AssignmentResponse {
    private Integer assignmentId;
    private String courseId;
    private String title;
    private String instructions;
    private String attachmentUrl;    
    private String downloadUrl; 
    private Integer maxScore;
    private LocalDateTime deadline;
    private LocalDateTime createdAt;
}
