package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;


import java.util.List;
import java.time.LocalDateTime;

@Entity
@Table(name = "submissionattachments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class SubmissionAttachment {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachmentid")
    private Integer attachmentId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "submissionid", nullable = false)
    private Submission submission;

    @Column(name = "fileurl", nullable = false)
    private String fileUrl;

    @Column(name = "filename", nullable = false)
    private String fileName;
}
