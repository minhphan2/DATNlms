package com.example.demo.DTO.SubmissionRequest;

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
    private Integer submissionId;
    private Integer assignmentId;
    private Integer studentId;
    private String fileUrl;
    private LocalDateTime submittedAt;
    private Double score;
    private String feedback;
}
