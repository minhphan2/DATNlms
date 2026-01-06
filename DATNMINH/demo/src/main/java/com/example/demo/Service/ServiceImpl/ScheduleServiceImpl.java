package com.example.demo.Service.ServiceImpl;

import com.example.demo.Service.Service.ScheduleService;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.ScheduleRepository;
import com.example.demo.DTO.ScheduleRequest.*;
import com.example.demo.mapper.ScheduleMapper;
import com.example.demo.model.Schedules;
import com.example.demo.DTO.ScheduleResponse.*;
import com.example.demo.model.Course;
import com.example.demo.model.Section;
import com.example.demo.model.Lesson;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.SectionRepository;
import com.example.demo.Repository.LessonRepository;

import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ScheduleServiceImpl implements ScheduleService {
    public final ScheduleRepository scheduleRepository;
    public final ScheduleMapper scheduleMapper;
    public final CourseRepository courseRepository;
    public final SectionRepository sectionRepository;
    public final LessonRepository lessonRepository;
    
    
    @Override
    public List<ScheduleResponse> findAll(){
        return scheduleRepository.findAll().stream()
        .map(scheduleMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public ScheduleResponse createSchedule(CreateRequest request){
        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("ko tim thay course"));
        
        Section section = sectionRepository.findById(request.getSectionId())
        .orElseThrow(() -> new RuntimeException("ko tim thay section"));

        Lesson lesson = lessonRepository.findById(request.getLessonId())
        .orElseThrow(() -> new RuntimeException("ko tim thay lesson"));

        Schedules schedule = scheduleMapper.toEntity(request, course, section, lesson);
        Schedules savedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toResponse(savedSchedule);
    }

    @Override
    public ScheduleResponse updateSchedule(Integer id, UpdateRequest request){
        Schedules schedule = scheduleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("ko tim thay schedule"));
       
        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("ko tim thya course"));

        Section section = sectionRepository.findById(request.getSectionId())
        .orElseThrow(() -> new RuntimeException("ko tim thay section"));

        Lesson lesson = lessonRepository.findById(request.getLessonId())
        .orElseThrow(() -> new RuntimeException("ko tim thay lesson"));

        scheduleMapper.updateEntity(request, schedule, course, section, lesson);
        Schedules updatedSchedule = scheduleRepository.save(schedule);
        return scheduleMapper.toResponse(updatedSchedule);
    }
    

    @Override
    public void deleteSchedule(Integer id){
        scheduleRepository.deleteById(id);
    }

    @Override
    public ScheduleResponse findById(Integer id){
        Schedules schedule = scheduleRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("ko tim thay schedule"));
        return scheduleMapper.toResponse(schedule);
    }

    @Override
    public List<ScheduleResponse> findByCourseId(Integer courseId){
        return scheduleRepository.findByCourseId(courseId).stream()
        .map(scheduleMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public List<ScheduleResponse> findScheduleByStudentId(Integer studentId){
        List<Schedules> schedules = scheduleRepository.findScheduleByStudentId(studentId);
        List<ScheduleResponse> result = new ArrayList<>();
        for(Schedules s : schedules){
            Lesson lesson = s.getLesson();
            Section section = s.getSection();
            Course course = s.getCourse();
           result.add(ScheduleResponse.builder()
                .scheduleId(s.getId())
                .courseId(course.getId())
                .courseName(course.getCourseName())
                .sectionId(section.getId())
                .sectionTitle(section.getTitle())
                .lessonId(lesson.getId())
                .lessonTitle(lesson.getTitle())
                .dayOfWeek(s.getDayOfWeek())
                .startTime(s.getStartTime())
                .endTime(s.getEndTime())
                .location(s.getLocation())
                .startDate(s.getStartDate())
                .endDate(s.getEndDate())
                .type(s.getType())
                .build());
        }
        return result;
    }
}
