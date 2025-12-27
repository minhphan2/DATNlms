package com.example.demo.Repository;
import com.example.demo.model.Schedules;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface ScheduleRepository extends JpaRepository<Schedules, Integer> {
    List<Schedules> findAll();
    List<Schedules> findByCourseId(Integer courseId);

    
}
