package com.example.demo.Service.ServiceImpl;

import org.springframework.stereotype.Service;

import com.example.demo.Repository.CourseRepository;
import com.example.demo.Repository.EnrollmentRepository;
import com.example.demo.Service.Service.EnrollmentService;
import com.example.demo.mapper.EnrollmentsMapper;
import com.example.demo.Repository.UserRepository;
import com.example.demo.DTO.EnrollmentRequest.*;
import com.example.demo.model.User;
import com.example.demo.model.Enrollment;
import com.example.demo.DTO.EnrollmentResponse.EnrollmentResponse;
import com.example.demo.model.Course;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class EnrollmentServiceImpl implements EnrollmentService {
    private final EnrollmentRepository enrollmentRepository;
    private final EnrollmentsMapper enrollmentsMapper;
    private final UserRepository userRepository;
    private final CourseRepository courseRepository;

    @Override
    public List<EnrollmentResponse> findAll(){
        return enrollmentRepository.findAll().stream()
        .map(enrollmentsMapper::toResponse)
        .collect(Collectors.toList());
    }


    @Override
public EnrollmentResponse createEnrollment(CreateRequest request){
    // 1. Lấy student
    User student = userRepository.findById(request.getUserId())
            .orElseThrow(()-> new RuntimeException("ko tim thay hoc sinh"));
    
    // 2. Kiểm tra phải student
    if(student.getRole() != User.Role.student){
        throw new RuntimeException("Chi hoc vien moi duoc dang ky");
    }
    
    // 3. Lấy course
    Course course = courseRepository.findById(request.getCourseId())
            .orElseThrow(()-> new RuntimeException("ko tim thay khoa hoc"));
    
    // 4. Kiểm tra đã đăng ký chưa
    if(enrollmentRepository.existsByStudentIdAndCourseId(
            request.getUserId(), request.getCourseId())){
        throw new RuntimeException("Da dang ky khoa hoc nay roi");
    }

    // 5. Kiểm tra số lượng học viên
    long enrolledCount = enrollmentRepository.countByCourseId(request.getCourseId());
    if(enrolledCount >= course.getMaxStudents()){
        throw new RuntimeException("Khoa hoc da day");
    }

    
    // 6. Tạo enrollment
    Enrollment enrollment = enrollmentsMapper.toEntity(request, student, course);
    Enrollment savedEnrollment = enrollmentRepository.save(enrollment);
    return enrollmentsMapper.toResponse(savedEnrollment);
}

}
