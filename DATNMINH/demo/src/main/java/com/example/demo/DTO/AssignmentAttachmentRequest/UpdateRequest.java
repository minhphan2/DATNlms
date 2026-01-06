package com.example.demo.DTO.AssignmentAttachmentRequest;
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
public class UpdateRequest {
    
    private Integer attachmentId;
    private Integer assignmentId;
    private String fileUrl;
    private String fileName;

}
