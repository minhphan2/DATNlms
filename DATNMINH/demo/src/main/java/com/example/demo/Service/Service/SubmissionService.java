package com.example.demo.Service.Service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import com.example.demo.DTO.SubmissionRequest.CreateRequest;
import com.example.demo.DTO.SubmissionRequest.UpdateRequest;
import com.example.demo.DTO.SubmissionResponse.SubmissionResponse;


public interface SubmissionService {
    SubmissionResponse createSubmission(CreateRequest request);

    SubmissionResponse updateSubmission(Integer id, UpdateRequest request);

    void deleteSubmission(Integer id);

    SubmissionResponse findById(Integer id);

    List<SubmissionResponse> findAll();

    List<SubmissionResponse> findByAssignmentId(Integer assignmentId);

    SubmissionResponse uploadAttachment(Integer id, MultipartFile file);

    void deleteAttachment(Integer id);

    byte[] downloadAttachment(Integer id);

    SubmissionResponse gradeSubmission(Integer id, Double score, String feedback);
    
    SubmissionResponse createSubmissionWithFiles(Integer assignmentId, Integer studentId, List<MultipartFile> files);

    SubmissionResponse findByAssignmentAndStudent(Integer assignmentId, Integer studentId);
    Integer countSubmissionsByAssignment(Integer assignmentId);
}