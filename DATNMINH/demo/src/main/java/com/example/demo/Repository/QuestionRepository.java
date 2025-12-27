package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import com.example.demo.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Integer> {

    List<Question> findAll();
    Optional<Question> findById(Integer id);
    List<Question> findByQuizQuizid(Integer quizId);
}

