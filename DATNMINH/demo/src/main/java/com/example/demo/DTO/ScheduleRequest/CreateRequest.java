package com.example.demo.DTO.ScheduleRequest;

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
    private Integer scheduleId;

    @NotNull(message = "course ID is required")
    private Integer courseId;

    @NotNull(message = "section ID is required")
    private Integer sectionId;

    @NotNull(message = "lesson ID is required")
    private Integer lessonId;

    @NotNull(message = "dayOfWeek is required")
    private String dayOfWeek;


    @NotNull(message = "startTime is required")
    private String startTime;

    @NotNull(message = "endTime is required")
    private String endTime;

    @NotNull(message = "location is required")
    private String location;

    @NotNull(message = "startDate is required")
    private LocalDateTime startDate;

    @NotNull(message = "endDate is required")
    private LocalDateTime endDate;

    @NotNull(message = "type is required")
    private String type;
}
