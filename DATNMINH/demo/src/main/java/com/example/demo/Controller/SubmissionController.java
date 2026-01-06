package com.example.demo.Controller;

import lombok.RequiredArgsConstructor;

import org.springframework.web.bind.annotation.*;
import org.springframework.http.ResponseEntity;
import java.util.List;

import com.example.demo.Service.Service.SubmissionService;
import com.example.demo.model.Assignment;
import com.example.demo.DTO.SubmissionRequest.CreateRequest;
import com.example.demo.DTO.SubmissionRequest.UpdateRequest;
import com.example.demo.DTO.AssignmentResponse.AssignmentResponse;
import com.example.demo.DTO.SubmissionRequest.ChamdiemRequest;
import com.example.demo.DTO.SubmissionResponse.SubmissionResponse;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

import jakarta.validation.Valid;




@RestController
@RequestMapping("api/submissions")
@RequiredArgsConstructor
public class SubmissionController {
    
    private final SubmissionService submissionService;

    @GetMapping
    public ResponseEntity<List<SubmissionResponse>> getAllSubmissions(){
        return ResponseEntity.ok(submissionService.findAll());
    }

    @GetMapping("byassignment/{assignmentId}")
    public ResponseEntity<List<SubmissionResponse>> getSubmissionByassignmentId(@PathVariable Integer assignmentId ){
        return ResponseEntity.ok(submissionService.findByAssignmentId(assignmentId));
    }

    @PostMapping
    public ResponseEntity<SubmissionResponse> createsubmission(@Valid @RequestBody CreateRequest request){
        SubmissionResponse submissionResponse = submissionService.createSubmission(request);
        return ResponseEntity.ok(submissionResponse);
    }
    
    @PostMapping("/{id}/upload")
    public ResponseEntity<SubmissionResponse> uploadAttachment(
        @PathVariable Integer id,
        @RequestParam("file") org.springframework.web.multipart.MultipartFile file
    ) {
        return ResponseEntity.ok(submissionService.uploadAttachment(id, file));
    }

    @PutMapping("/{id}/grade")
    public ResponseEntity<SubmissionResponse> gradeSubmission(
        @PathVariable Integer id,
        @Valid @RequestBody ChamdiemRequest request
    ) {
        SubmissionResponse submissionResponse = submissionService.gradeSubmission(id, request.getScore(), request.getFeedback());
        return ResponseEntity.ok(submissionResponse);
    }

    @DeleteMapping("/{id}/deleteAttachment")
    public ResponseEntity<Void> deleteAttachment(@PathVariable Integer id){
        submissionService.deleteAttachment(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadAttachment(@PathVariable Integer id){
       SubmissionResponse submissionResponse = submissionService.findById(id);
         byte[] fileData = submissionService.downloadAttachment(id);

        return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + submissionResponse.getStudentId() + "\"")
        .contentType(MediaType.APPLICATION_OCTET_STREAM)
        .body(fileData);
    }

    @GetMapping("/by-assignment-and-student")
    public ResponseEntity<SubmissionResponse> getSubmissionByAssignmentAndStudent(
        @RequestParam Integer assignmentId,
        @RequestParam Integer studentId
    ) {
        SubmissionResponse submissionResponse = submissionService.findByAssignmentAndStudent(assignmentId, studentId);
        return ResponseEntity.ok(submissionResponse);
    }
    
    
}
