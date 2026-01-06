package com.example.demo.Repository;
import com.example.demo.model.Schedules;

import org.springframework.data.repository.query.Param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Integer> {
    List<Schedules> findAll();
    List<Schedules> findByCourseId(Integer courseId);

    @Query("SELECT s FROM Schedules s JOIN Enrollment e ON s.course = e.course WHERE e.student.id = :studentId")
List<Schedules> findScheduleByStudentId(@Param("studentId") Integer studentId);
}
    
