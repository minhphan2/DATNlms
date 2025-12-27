package com.example.demo.mapper;



import org.springframework.stereotype.Component;
import com.example.demo.model.Course;
import com.example.demo.model.Enrollment;
import com.example.demo.model.User;
import com.example.demo.DTO.EnrollmentResponse.EnrollmentResponse;
import com.example.demo.DTO.CourseRequest.UpdateRequest;
import com.example.demo.DTO.EnrollmentRequest.CreateRequest;






@Component
public class EnrollmentsMapper {
    
    public EnrollmentResponse toResponse(Enrollment enrollment){
        return EnrollmentResponse.builder()
        .id(enrollment.getId())
        .courseName(enrollment.getCourse().getCourseName())
        .userFullName(enrollment.getStudent().getFullname())
        .enrollmentDate(enrollment.getEnrolledAt())
        .build();
    }

    public Enrollment toEntity(CreateRequest request, User student , Course course){
        return Enrollment.builder()
        .course(course)
        .student(student)
        .build();
    }

    public void updateEntity(Enrollment enrollment, UpdateRequest request, User student, Course course){
        enrollment.setStudent(student);
        enrollment.setCourse(course);
    }
}
