package com.example.demo.DTO.ScheduleResponse;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

import com.example.demo.model.Schedules;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleResponse {
    private Integer scheduleId;
    private Integer courseId;
    private String courseName;
    private Integer sectionId;
    private Integer lessonId;
    private Schedules.DOWeek dayOfWeek;
    private String startTime;
    private String endTime;
    private String location;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Schedules.type type;
}
