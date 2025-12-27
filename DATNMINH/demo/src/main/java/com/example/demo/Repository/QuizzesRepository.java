package com.example.demo.Repository;
import org.springframework.stereotype.Repository;
import com.example.demo.model.Quizzes;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuizzesRepository extends JpaRepository<Quizzes, Integer> {
    List<Quizzes> findAll();
    Optional<Quizzes> findById(Integer id);
    List<Quizzes> findByCourseId(Integer courseId);
}
