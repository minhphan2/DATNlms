package com.example.demo.Repository;


import com.example.demo.DTO.AssignmentResponse.AssignmentResponse;
import com.example.demo.model.Assignment;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//khi nao can tim theo trang thi dung query
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AssignmentRepository extends JpaRepository<Assignment, Integer> {
 
    List<Assignment> findAll();
    Optional<Assignment> findById(Integer id);
    List<Assignment> findByCourseId(Integer courseId);
}
