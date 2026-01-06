package com.example.demo.Service.Service;


import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import com.example.demo.DTO.AssignmentAttachmentRequest.*;
import com.example.demo.DTO.AssignmentAttachmentResponse.*;

@Service
public interface AssignmentAttachmentService {

List<AssignmentAttachmentResponse> uploadAssignmentAttachments(Integer assignmentId, List<MultipartFile> files);
List<AssignmentAttachmentResponse> getAttachmentsByAssignment(Integer assignmentId);
byte[] downloadAssignmentAttachment(Integer attachmentId);
AssignmentAttachmentResponse getAttachmentResponse(Integer attachmentId);
} 
