package com.example.demo.mapper;


import org.springframework.stereotype.Component;
import com.example.demo.model.Schedules;
import com.example.demo.DTO.ScheduleResponse.ScheduleResponse;
import com.example.demo.DTO.ScheduleRequest.UpdateRequest;
import com.example.demo.model.Course;
import com.example.demo.model.Section;
import com.example.demo.model.Lesson;
import com.example.demo.DTO.ScheduleRequest.CreateRequest;



@Component
public class ScheduleMapper {
    public ScheduleResponse toResponse(Schedules schedule)
    {
        return ScheduleResponse.builder()
        .scheduleId(schedule.getId())
        .courseId(schedule.getCourse().getId())
        .courseName(schedule.getCourse().getCourseName())
        .sectionId(schedule.getSection().getId())
        .lessonId(schedule.getLesson().getId())
        .dayOfWeek(schedule.getDayOfWeek())
        .startTime(schedule.getStartTime())
        .endTime(schedule.getEndTime())
        .location(schedule.getLocation())
        .startDate(schedule.getStartDate())
        .endDate(schedule.getEndDate())
        .type(schedule.getType())
        .build();
    }

    public Schedules toEntity(CreateRequest request, Course course, Section section, Lesson lesson){
        return Schedules.builder()
        .course(course)
        .section(section)
        .lesson(lesson)
        .dayOfWeek(Schedules.DOWeek.valueOf(request.getDayOfWeek()))
        .startTime(request.getStartTime())
        .endTime(request.getEndTime())
        .location(request.getLocation())
        .startDate(request.getStartDate())
        .endDate(request.getEndDate())
        .type(Schedules.type.valueOf(request.getType()))
        .build();
    }


    public void updateEntity(UpdateRequest request, Schedules schedule, Course course, Section section, Lesson lesson){
        schedule.setCourse(course);
        schedule.setSection(section);
        schedule.setLesson(lesson);
        schedule.setDayOfWeek(Schedules.DOWeek.valueOf(request.getDayOfWeek()));
        schedule.setStartTime(request.getStartTime());
        schedule.setEndTime(request.getEndTime());
        schedule.setLocation(request.getLocation());
        schedule.setStartDate(request.getStartDate());
        schedule.setEndDate(request.getEndDate());
        schedule.setType(Schedules.type.valueOf(request.getType()));
    }
}
