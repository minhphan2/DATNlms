package com.example.demo.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import com.example.demo.Repository.SubmissionRepository;
import com.example.demo.Repository.AssignmentRepository;
import com.example.demo.Repository.UserRepository;
import com.example.demo.Service.Service.SubmissionService;
import com.example.demo.mapper.SubmissionMapper;
import com.example.demo.DTO.SubmissionRequest.CreateRequest;
import com.example.demo.DTO.SubmissionRequest.UpdateRequest;
import com.example.demo.DTO.SubmissionResponse.SubmissionResponse;
import com.example.demo.model.User;

import com.example.demo.model.Submission;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.Assignment;
import com.example.demo.Service.Service.FileStorageService;


import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionServiceImpl implements SubmissionService {
    private final SubmissionRepository submissionRepository;
    private final SubmissionMapper submissionMapper;
    private final FileStorageService fileStorageService;
    private final AssignmentRepository assignmentRepository;
    private final UserRepository userRepository;



    @Override
    public SubmissionResponse createSubmission(CreateRequest request)
    {

        if(submissionRepository.existsByAssignment_AssignmentIdAndStudent_Id(
            request.getAssignmentId(), request.getStudentId())){
            throw new RuntimeException("Bạn đã nộp rồi");
        }

        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
        .orElseThrow(() -> new RuntimeException("khong tim thay assignment"));

        User user = userRepository.findById(request.getStudentId())
        .orElseThrow(() -> new RuntimeException("khong tim thay hoc sinh"));

        Submission submission = submissionMapper.toEntity(request, assignment, user);
        Submission savedSubmission = submissionRepository.save(submission);
        return submissionMapper.toResponse(savedSubmission);
    }

    @Override
    public SubmissionResponse updateSubmission(Integer id , UpdateRequest request){
        Submission submission = submissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay submission"));

        Assignment assignment = assignmentRepository.findById(request.getAssignmentId())
        .orElseThrow(() -> new RuntimeException("khong tim thay assignment"));

        User user = userRepository.findById(request.getStudentId())
        .orElseThrow(() -> new RuntimeException("khong tim thay hoc sinh"));

        submissionMapper.updateEntity(submission, request, assignment, user);
        Submission updatedSubmission = submissionRepository.save(submission);
        return submissionMapper.toResponse(updatedSubmission);
    }

    @Override
    public void deleteSubmission(Integer id)
    {
        submissionRepository.deleteById(id);
    }

    @Override
    public SubmissionResponse findById(Integer id)
    {
        Submission submission = submissionRepository.findById(id)
       .orElseThrow(() -> new RuntimeException("khong tim thay submission"));
        return submissionMapper.toResponse(submission);
    }

    @Override
    public List<SubmissionResponse> findAll()
    {
        List<Submission> submissions = submissionRepository.findAll();
        return submissions.stream()
        .map(submissionMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public List<SubmissionResponse> findByAssignmentId(Integer assignmentId)
    {
        List<Submission> submissions = submissionRepository.findByAssignment_AssignmentId(assignmentId);
        return submissions.stream()
        .map(submissionMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public SubmissionResponse uploadAttachment(Integer id, MultipartFile file){
        Submission submission = submissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay submission"));

        String fileUrl = fileStorageService.storeFile(file, "submissions");
        submission.setFileUrl(fileUrl);
        Submission updatedSubmission = submissionRepository.save(submission);
        return submissionMapper.toResponse(updatedSubmission);
    }

    @Override
    public void deleteAttachment(Integer id){
        Submission submission = submissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay submission"));

        if(submission.getFileUrl() != null){
            fileStorageService.deleteFile(submission.getFileUrl());
            submission.setFileUrl(null);
            submissionRepository.save(submission);
        }
    }

    @Override
    public byte[] downloadAttachment(Integer id){
        Submission submission = submissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay submission"));
        return fileStorageService.loadFile(submission.getFileUrl());
    }

    @Override
    public SubmissionResponse gradeSubmission(Integer id, Double score, String feedback){
        Submission submission = submissionRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay submission"));

        submission.setScore(score);
        submission.setFeedback(feedback);

        Submission updatedSubmission = submissionRepository.save(submission);
        return submissionMapper.toResponse(updatedSubmission);
    }

    @Override
    public SubmissionResponse findByAssignmentAndStudent(Integer assignmentId, Integer studentId){
        Submission submission = submissionRepository.findByAssignment_AssignmentIdAndStudent_Id(assignmentId, studentId)
        .orElseThrow(() -> new RuntimeException("khong tim thay submission"));
        return submissionMapper.toResponse(submission);
    }

    
}
