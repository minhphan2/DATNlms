package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;

@Entity
@Table(name = "submissions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Submission {
    @Id 
    @GeneratedValue(strategy = GenerationType.IDENTITY)  
    @Column(name = "submissionid")
    private Integer submissionid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "assignmentid", nullable = false)
    @JsonIgnore
    private Assignment assignment;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "studentid", nullable = false)
    private User student;

    @Column(name = "fileurl", nullable = false)
    private String fileUrl;

    @CreationTimestamp
    @Column(name = "submittedat", nullable = false)
    private LocalDateTime submittedAt;

    @Column(name = "score")
    private Double score;

    @Column(name = "feedback", columnDefinition = "TEXT")
    private String feedback;
}
