package com.example.demo.DTO.SectionRequest;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;

import java.time.LocalDateTime;


import jakarta.validation.constraints.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequest {
    
    @NotNull(message = "Course ID is required")
    private Integer courseId;

    @NotNull(message = "title is required")
    private String title;

    @NotNull(message = "position is required")
    private Integer position;
    

    @NotNull(message = "startDate is required")
    private LocalDateTime startDate;

    @NotNull(message = "endDate is required")
    private LocalDateTime endDate;
}