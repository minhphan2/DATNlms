package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.example.demo.Service.Service.SubmissionAttachmentService;
import com.example.demo.DTO.SubmissionAttachmentResponse.SubmissionAttachmentResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/api/submissions/attachments")
@RequiredArgsConstructor
public class SubmissionAttachmentController {
    private final SubmissionAttachmentService submissionAttachmentService;

    @PostMapping("/{submissionId}")
    public ResponseEntity<List<SubmissionAttachmentResponse>> uploadSubmissionAttachment(
            @PathVariable Integer submissionId,
            @RequestParam("files") List<MultipartFile> files
    ) {
        List<SubmissionAttachmentResponse> responses = submissionAttachmentService.uploadSubmissionAttachment(submissionId, files);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/{submissionId}")
    public ResponseEntity<List<SubmissionAttachmentResponse>> getSubmissionAttachment(
            @PathVariable Integer submissionId
    ) {
        List<SubmissionAttachmentResponse> responses = submissionAttachmentService.getSubmissionAttachment(submissionId);
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/download/{attachmentId}")
    public ResponseEntity<byte[]> downloadSubmissionAttachment(
            @PathVariable Integer attachmentId
    ) {
        SubmissionAttachmentResponse attachment = submissionAttachmentService.getSubmissionAttachmentResponse(attachmentId);
        byte[] filecontent = submissionAttachmentService.downloadSubmissionAttachment(attachmentId);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + attachment.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(filecontent);
    }
    
}
