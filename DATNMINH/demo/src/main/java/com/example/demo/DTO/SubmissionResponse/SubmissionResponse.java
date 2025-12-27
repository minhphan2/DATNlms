package com.example.demo.DTO.SubmissionResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionResponse {
    private Integer submissionId;
    private Integer assignmentId;
    private Integer studentId;
    private String fileUrl;
    private String downloadUrl;
    private LocalDateTime submittedAt;
    private Double score;
    private String feedback;
}
