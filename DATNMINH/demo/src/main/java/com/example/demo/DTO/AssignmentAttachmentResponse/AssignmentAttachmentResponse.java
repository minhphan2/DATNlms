package com.example.demo.DTO.AssignmentAttachmentResponse;

import com.example.demo.model.AssignmentAttachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentAttachmentResponse {
    private Integer attachmentId;
    private Integer assignmentId;
    private String fileUrl;
    private String fileName;
}
