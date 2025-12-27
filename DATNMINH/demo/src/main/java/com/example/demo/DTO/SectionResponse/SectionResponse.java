package com.example.demo.DTO.SectionResponse;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SectionResponse {
    private Integer id;
    private String  courseName;
    private String title;
    private Integer position;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}