package com.example.demo.mapper;


import org.springframework.stereotype.Component;
import com.example.demo.model.AssignmentAttachment;
import com.example.demo.DTO.AssignmentAttachmentRequest.CreateRequest;
import com.example.demo.DTO.AssignmentAttachmentRequest.UpdateRequest;
import com.example.demo.DTO.AssignmentAttachmentResponse.AssignmentAttachmentResponse;
import com.example.demo.model.Assignment;



@Component
public class AssignmentAttachmentMapper {
    public AssignmentAttachment toEntity(CreateRequest createRequest, Assignment assignment, String fileUrl, String fileName){
        return AssignmentAttachment.builder()
        .assignment(assignment)
        .fileUrl(fileUrl)
        .fileName(fileName)
        .build();
    }

    public void updateEntity(AssignmentAttachment attachment, UpdateRequest request, Assignment assignment){
        attachment.setAssignment(assignment);
        attachment.setFileUrl(request.getFileUrl());
        attachment.setFileName(request.getFileName());
    }

    public AssignmentAttachmentResponse toResponse(AssignmentAttachment attachment){
        return AssignmentAttachmentResponse.builder()
        .attachmentId(attachment.getAttachmentId())
        .assignmentId(attachment.getAssignment().getAssignmentId())
        .fileUrl(attachment.getFileUrl())
        .fileName(attachment.getFileName())
        .build();
    }



}    