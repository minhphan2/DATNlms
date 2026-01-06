package com.example.demo.Service.ServiceImpl;
import lombok.RequiredArgsConstructor;
import java.util.stream.Collectors;
import com.example.demo.Repository.LessonRepository;
import com.example.demo.model.AssignmentAttachment;
import com.example.demo.model.Assignment;
import com.example.demo.Service.Service.FileStorageService;
import com.example.demo.Repository.AssignmentAttachmentRepository;
import com.example.demo.Repository.AssignmentRepository;
import com.example.demo.mapper.AssignmentAttachmentMapper;
import com.example.demo.Service.Service.AssignmentAttachmentService;
import com.example.demo.DTO.AssignmentAttachmentRequest.CreateRequest;
import com.example.demo.DTO.AssignmentAttachmentResponse.AssignmentAttachmentResponse;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;




@Service
@RequiredArgsConstructor
public class AssignmentAttachmentServiceImpl implements AssignmentAttachmentService {
    private final AssignmentAttachmentRepository assignmentAttachmentRepository;
    private final AssignmentRepository assignmentRepository;
    private final AssignmentAttachmentMapper assignmentAttachmentMapper;
    private final FileStorageService fileStorageService;

    @Override
    public List<AssignmentAttachmentResponse> uploadAssignmentAttachments(Integer assignmentId, List<MultipartFile> files) {
        Assignment assignment = assignmentRepository.findById(assignmentId)
        .orElseThrow(() -> new RuntimeException("Assignment not found"));


        List<AssignmentAttachmentResponse> responses = new ArrayList<>();

        for (MultipartFile file : files) {
            String fileUrl = fileStorageService.storeFile(file, "assignments");
            String fileName = file.getOriginalFilename();

            AssignmentAttachment attachment = assignmentAttachmentMapper.toEntity(
                CreateRequest.builder()
                .assignmentId(assignmentId)
                .file(file)
                .build(),
                assignment,
                fileUrl,
                fileName
            );

            assignmentAttachmentRepository.save(attachment);

              // Thêm response vào list
        AssignmentAttachmentResponse response = assignmentAttachmentMapper.toResponse(attachment);
        responses.add(response);

        }
        return responses;
    }

    @Override
    public List<AssignmentAttachmentResponse> getAttachmentsByAssignment(Integer assignmentId) {
        List<AssignmentAttachment> attachments = assignmentAttachmentRepository.findByAssignment_AssignmentId(assignmentId);
        return attachments.stream()
        .map(assignmentAttachmentMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public byte[] downloadAssignmentAttachment(Integer attachmentId) {
        AssignmentAttachment attachment = assignmentAttachmentRepository.findById(attachmentId)
        .orElseThrow(() -> new RuntimeException("Attachment not found"));

        return fileStorageService.loadFile(attachment.getFileUrl());
    }

    @Override
    public AssignmentAttachmentResponse getAttachmentResponse(Integer attachmentId) {
        AssignmentAttachment attachment = assignmentAttachmentRepository.findById(attachmentId)
        .orElseThrow(() -> new RuntimeException("Attachment not found"));

        return assignmentAttachmentMapper.toResponse(attachment);
    }

    
    
}
