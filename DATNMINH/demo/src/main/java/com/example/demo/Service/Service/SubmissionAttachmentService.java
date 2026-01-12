package com.example.demo.Service.Service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.example.demo.DTO.SubmissionAttachmentRequest.*;
import com.example.demo.DTO.SubmissionAttachmentResponse.*;


@Service
public interface SubmissionAttachmentService {
    List<SubmissionAttachmentResponse> uploadSubmissionAttachment(Integer submissionId, List<MultipartFile> files);
    List<SubmissionAttachmentResponse> getSubmissionAttachment(Integer submissionId);
    byte [] downloadSubmissionAttachment(Integer submissionId);
    SubmissionAttachmentResponse getSubmissionAttachmentResponse(Integer attachmentId);
    
} 
