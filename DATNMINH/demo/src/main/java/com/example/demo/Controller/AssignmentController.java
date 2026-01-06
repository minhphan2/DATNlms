package com.example.demo.Controller;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import lombok.RequiredArgsConstructor;

import com.example.demo.Service.Service.AssignmentService;

import jakarta.mail.Multipart;
import jakarta.validation.Valid;

import com.example.demo.DTO.AssignmentRequest.CreateRequest;
import com.example.demo.DTO.AssignmentRequest.UpdateRequest;
import com.example.demo.DTO.AssignmentResponse.AssignmentResponse;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.http.MediaType;


import org.springframework.http.HttpHeaders;
import java.util.List;


@RestController
@RequestMapping("api/assignments")
@RequiredArgsConstructor
public class AssignmentController {
    private final AssignmentService assignmentService;

    @GetMapping
    public ResponseEntity <List<AssignmentResponse>> findAllAssignments(){
        return ResponseEntity.ok(assignmentService.findAll());
    }

    @GetMapping("/course/{courseId}")
    public ResponseEntity <List<AssignmentResponse>> findAssignmentByCourse(@PathVariable Integer courseId){
        return ResponseEntity.ok(assignmentService.findByCourseId(courseId));
    }

    @GetMapping("/{id}")
    public ResponseEntity<AssignmentResponse> getAssignmentById(@PathVariable Integer id){
        AssignmentResponse assignmentResponse = assignmentService.findById(id);
        return ResponseEntity.ok(assignmentResponse);
    }

    @PostMapping
    public ResponseEntity<AssignmentResponse> createAssignment(
        @Valid @RequestBody CreateRequest request
    ) {
        return ResponseEntity.ok(assignmentService.createAssignment(request));
    }

    @PostMapping("/upload/{id}")
    public ResponseEntity<AssignmentResponse> uploadAttachment(
        @PathVariable Integer id,
        @RequestParam("file") MultipartFile file
    ) {
        return ResponseEntity.ok(assignmentService.uploadAttachment(id, file));
    }


   @GetMapping("/dowload/{id}")
public ResponseEntity<byte[]> downloadAttachment(@PathVariable Integer id )
{
    AssignmentResponse assignmentResponse = assignmentService.findById(id);
    byte[] filecontent = assignmentService.downloadAttachment(id);

   String url = assignmentResponse.getAttachmentUrl();
String filename = "attachment";
if (url != null && url.contains("/")) {
    filename = url.substring(url.lastIndexOf("/") + 1); // lấy phần sau dấu "/"
}
return ResponseEntity.ok()
    .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + filename + "\"")
    .contentType(MediaType.APPLICATION_OCTET_STREAM)
    .body(filecontent);
}


    
}
