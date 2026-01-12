package com.example.demo.mapper;


import org.springframework.stereotype.Component;
import com.example.demo.model.Course;
import com.example.demo.model.User;
import com.example.demo.DTO.CourseResponse.CourseResponse;
import com.example.demo.DTO.DepartmentResponse.DepartmentResponse;
import com.example.demo.DTO.CourseRequest.CreateRequest;
import com.example.demo.DTO.CourseRequest.UpdateRequest;


@Component  
public class CourseMapper {
    
    public CourseResponse toResponse(Course course) {
      DepartmentResponse deptDto = null;
    if (course.getDepartment() != null) {
        deptDto = DepartmentResponse.builder()
            .id(course.getDepartment().getId())
            .name(course.getDepartment().getName())
            .build();
    }
        return CourseResponse.builder()
                .id(course.getId())
                .courseName(course.getCourseName())
                .title(course.getTitle())
                .teacherName(course.getTeacher().getFullname())
                .description(course.getDescription())
                .category(course.getCategory())
                .thumbnail(course.getThumbnail())
                .maxStudents(course.getMaxStudents())
                .status(course.getStatus())
                .department(deptDto)
                .createdAt(course.getCreatedAt())
                .build();
    }

      public Course toEntity(CreateRequest request, User teacher) {
        return Course.builder()
                .courseName(request.getCourseName())
                .title(request.getTitle())
                .teacher(teacher)
                .description(request.getDescription())
                .category(request.getCategory())
                .startDate(request.getStartDate())
                .endDate(request.getEndDate())
                .maxStudents(request.getMaxStudents())
                .status(request.getStatus())
                .build();
    }

    public void updateEntity(Course course, UpdateRequest request, User teacher)
    {
        course.setTeacher(teacher);
        course.setCourseName(request.getCourseName());
        course.setTitle(request.getTitle());
        course.setDescription(request.getDescription());
        course.setStatus(request.getStatus());
        course.setCategory(request.getCategory());
        course.setMaxStudents(request.getMaxStudents());
        course.setStartDate(request.getStartDate());
        course.setEndDate(request.getEndDate());

    }
}
