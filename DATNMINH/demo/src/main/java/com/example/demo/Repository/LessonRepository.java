package com.example.demo.Repository;
import com.example.demo.model.Lesson;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LessonRepository extends JpaRepository<Lesson, Integer> {
    List<Lesson> findAll();
    List<Lesson> findBySectionId(Integer sectionId);
    
}
