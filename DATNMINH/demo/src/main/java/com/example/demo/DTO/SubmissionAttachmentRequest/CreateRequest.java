package com.example.demo.DTO.SubmissionAttachmentRequest;

import com.example.demo.model.SubmissionAttachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import org.springframework.web.multipart.MultipartFile;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequest {
    private Integer submissionId;
    private MultipartFile file;
}
