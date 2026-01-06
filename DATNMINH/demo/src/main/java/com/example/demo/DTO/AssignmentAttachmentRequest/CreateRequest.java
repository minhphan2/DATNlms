package com.example.demo.DTO.AssignmentAttachmentRequest;

import com.example.demo.model.AssignmentAttachment;
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
    private Integer assignmentId;
    private MultipartFile file;
}
