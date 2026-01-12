package com.example.demo.Repository;

import org.springframework.stereotype.Repository;   
import com.example.demo.model.SubmissionAttachment;
import org.springframework.data.jpa.repository.JpaRepository;


import java.util.List;
import java.util.Optional;

@Repository
public interface SubmissionAttachmentRepository extends JpaRepository<SubmissionAttachment, Integer> {
    List<SubmissionAttachment> findBySubmission_Submissionid(Integer submissionId);
    Optional<SubmissionAttachment> findByAttachmentId(Integer attachmentId);

    
}
