package com.example.demo.Controller;

import com.example.demo.Service.Service.AssignmentAttachmentService;
import com.example.demo.DTO.AssignmentAttachmentResponse.AssignmentAttachmentResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/assignments")
@RequiredArgsConstructor
public class AssignmentAttachmentController {

    private final AssignmentAttachmentService assignmentAttachmentService;

    // Upload nhiều file cho một assignment
    @PostMapping("/{assignmentId}/attachments")
    public ResponseEntity<List<AssignmentAttachmentResponse>> uploadAttachments(
            @PathVariable Integer assignmentId,
            @RequestParam("files") List<MultipartFile> files
    ) {
        List<AssignmentAttachmentResponse> responses = assignmentAttachmentService.uploadAssignmentAttachments(assignmentId, files);
        return ResponseEntity.ok(responses);
    }

    // Lấy danh sách file đính kèm của một assignment
    @GetMapping("/{assignmentId}/attachments")
    public ResponseEntity<List<AssignmentAttachmentResponse>> getAttachments(
            @PathVariable Integer assignmentId
    ) {
        List<AssignmentAttachmentResponse> responses = assignmentAttachmentService.getAttachmentsByAssignment(assignmentId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/attachments/download/{attachmentId}")
    public ResponseEntity<byte[]> downloadAttachment(
            @PathVariable Integer attachmentId
    ) {
        AssignmentAttachmentResponse attachment = assignmentAttachmentService.getAttachmentResponse(attachmentId);
        byte[] filecontent = assignmentAttachmentService.downloadAssignmentAttachment(attachmentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(filecontent);
    }
}