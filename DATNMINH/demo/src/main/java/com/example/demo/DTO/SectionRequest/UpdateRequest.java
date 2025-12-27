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
public class UpdateRequest {
    @NotNull(message = "Course ID khong duoc thieu")
    private Integer courseId;

    @NotNull(message = "Title khong duoc thieu")
    private String title;

    @NotNull(message = "Position khong duoc thieu")
    private Integer position;


    @NotNull(message = "startDate is required")
    private LocalDateTime startDate;

    @NotNull(message = "endDate is required")
    private LocalDateTime endDate;

}