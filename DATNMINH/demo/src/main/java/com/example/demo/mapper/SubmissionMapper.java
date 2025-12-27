package com.example.demo.mapper;

import org.springframework.stereotype.Component;

import com.example.demo.DTO.SubmissionResponse.SubmissionResponse;
import com.example.demo.model.Submission;
import com.example.demo.model.Assignment;
import com.example.demo.model.User;

import com.example.demo.DTO.SubmissionRequest.CreateRequest;
import com.example.demo.DTO.SubmissionRequest.UpdateRequest;



@Component
public class SubmissionMapper {
    public SubmissionResponse toResponse(Submission submission){
        return SubmissionResponse.builder()
        .submissionId(submission.getSubmissionid())
        .assignmentId(submission.getAssignment().getAssignmentId())
        .studentId(submission.getStudent().getId())
        .fileUrl(submission.getFileUrl())
        .downloadUrl(submission.getFileUrl() != null 
                    ? "/api/submissions/download/" + submission.getSubmissionid() 
                    : null)
        .submittedAt(submission.getSubmittedAt())
        .score(submission.getScore())
        .feedback(submission.getFeedback())
        .build();
    }


    public Submission toEntity(CreateRequest CreateRequest, Assignment assignment, User student){
        return Submission.builder()
        .assignment(assignment)
        .student(student)
        .fileUrl(CreateRequest.getFileUrl())
        .build();
    }

    public void updateEntity(Submission submission,UpdateRequest request, Assignment assignment, User student){
        submission.setAssignment(assignment);
        submission.setStudent(student);
        submission.setFileUrl(request.getFileUrl());
        submission.setSubmittedAt(request.getSubmittedAt());
        submission.setScore(request.getScore());
        submission.setFeedback(request.getFeedback());
    }

}
