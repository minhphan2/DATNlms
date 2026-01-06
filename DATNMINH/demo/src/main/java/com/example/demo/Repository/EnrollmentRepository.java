package com.example.demo.Repository;



import com.example.demo.model.Enrollment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//khi nao can tim theo trang thi dung query
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Repository;


@Repository
public interface EnrollmentRepository extends JpaRepository<Enrollment, Integer> {
    
    List<Enrollment> findAll();
    boolean existsByStudentIdAndCourseId(Integer studentId, Integer courseId);
    
    List<Enrollment> findByStudentId(Integer studentId);

    long countByCourseId(Integer courseId);

} 