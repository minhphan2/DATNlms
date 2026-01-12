package com.example.demo.Service.ServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import com.example.demo.Service.Service.SubmissionAttachmentService;
import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import java.util.ArrayList;
import com.example.demo.DTO.SubmissionAttachmentRequest.*;
import com.example.demo.DTO.SubmissionAttachmentResponse.*;
import com.example.demo.model.SubmissionAttachment;
import com.example.demo.Repository.SubmissionAttachmentRepository;
import com.example.demo.Repository.SubmissionRepository;
import com.example.demo.mapper.SubmissionAttachmentMapper;
import com.example.demo.Service.Service.FileStorageService;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class SubmissionAttachmentServiceImpl implements SubmissionAttachmentService {
private final SubmissionAttachmentRepository submissionAttachmentRepository;
private final SubmissionRepository submissionRepository;
private final SubmissionAttachmentMapper submissionAttachmentMapper;
private final FileStorageService fileStorageService;

@Override
public List<SubmissionAttachmentResponse> uploadSubmissionAttachment(Integer submissionId, List<MultipartFile> files) {
    var submission = submissionRepository.findById(submissionId)
        .orElseThrow(() -> new RuntimeException("Submission not found"));

    List<SubmissionAttachmentResponse> responses = new ArrayList<>();

    for (MultipartFile file : files) {
        String fileUrl = fileStorageService.storeFile(file, "submissions");
        String fileName = file.getOriginalFilename();

        SubmissionAttachment attachment = submissionAttachmentMapper.toEntity(
            CreateRequest.builder()
            .submissionId(submissionId)
            .file(file)
            .build(),
            submission,
            fileUrl,
            fileName
        );

        submissionAttachmentRepository.save(attachment);

        SubmissionAttachmentResponse response = submissionAttachmentMapper.toResponse(attachment);
        responses.add(response);
    }
    return responses;
}

@Override
public List<SubmissionAttachmentResponse> getSubmissionAttachment(Integer submissionId) {
    var attachments = submissionAttachmentRepository.findBySubmission_Submissionid(submissionId);
    return attachments.stream()
        .map(submissionAttachmentMapper::toResponse)
        .collect(Collectors.toList());
}

@Override
public byte[] downloadSubmissionAttachment(Integer attachmentId) {
    var attachment = submissionAttachmentRepository.findById(attachmentId)
        .orElseThrow(() -> new RuntimeException("Attachment not found"));
    return fileStorageService.loadFile(attachment.getFileUrl());
}

@Override
public SubmissionAttachmentResponse getSubmissionAttachmentResponse(Integer attachmentId) {
    var attachment = submissionAttachmentRepository.findById(attachmentId)
        .orElseThrow(() -> new RuntimeException("Attachment not found"));
    return submissionAttachmentMapper.toResponse(attachment);
}
}
