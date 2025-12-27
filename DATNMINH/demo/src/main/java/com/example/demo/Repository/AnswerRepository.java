package com.example.demo.Repository;

import org.springframework.stereotype.Repository;
import com.example.demo.model.Answer;
import com.example.demo.model.Question;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AnswerRepository  extends JpaRepository<Answer, Integer>{

    List<Answer> findAll();
    Optional<Answer> findById(Integer id);
    List<Answer> findByQuestionQuestionid(Integer questionId);
    
} 