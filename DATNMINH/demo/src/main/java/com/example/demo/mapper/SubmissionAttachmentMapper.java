package com.example.demo.mapper;

import org.springframework.stereotype.Component;
import com.example.demo.model.SubmissionAttachment;
import com.example.demo.DTO.SubmissionAttachmentRequest.CreateRequest;
import com.example.demo.DTO.SubmissionAttachmentRequest.UpdateRequest;
import com.example.demo.DTO.SubmissionAttachmentResponse.SubmissionAttachmentResponse;
import com.example.demo.model.Submission;

@Component
public class SubmissionAttachmentMapper {
    public SubmissionAttachment toEntity(CreateRequest createRequest, Submission submission, String fileUrl, String fileName){
        return SubmissionAttachment.builder()
        .submission(submission)
        .fileUrl(fileUrl)
        .fileName(fileName)
        .build();
    }

    public void updateEntity(SubmissionAttachment attachment, UpdateRequest request, Submission submission){
        attachment.setSubmission(submission);
        attachment.setFileUrl(request.getFileUrl());
        attachment.setFileName(request.getFileName());
    }

    public SubmissionAttachmentResponse toResponse(SubmissionAttachment attachment){
        return SubmissionAttachmentResponse.builder()
        .attachmentId(attachment.getAttachmentId())
        .submissionId(attachment.getSubmission().getSubmissionid())
        .fileUrl(attachment.getFileUrl())
        .fileName(attachment.getFileName())
        .build();
    }
}
