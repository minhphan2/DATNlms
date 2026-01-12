package com.example.demo.Repository;

import com.example.demo.model.Submission;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionRepository extends JpaRepository<Submission, Integer> {
    List<Submission> findAll();
    Optional<Submission> findById(Integer id);
    List<Submission> findByAssignment_AssignmentId(Integer assignmentId);
    List<Submission> findByStudentId(Integer studentId);
    boolean existsBySubmissionid(Integer submissionid);
    boolean existsByAssignment_AssignmentIdAndStudent_Id(Integer assignmentId, Integer studentId);
    
    Optional<Submission> findByAssignment_AssignmentIdAndStudent_Id(Integer assignmentId, Integer studentId);
    Integer countByAssignment_AssignmentId(Integer assignmentId);
}
