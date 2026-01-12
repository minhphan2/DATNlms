package com.example.demo.DTO.SubmissionAttachmentResponse;
import com.example.demo.model.SubmissionAttachment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionAttachmentResponse {
    private Integer attachmentId;
    private Integer submissionId;
    private String fileUrl;
    private String fileName;
}
