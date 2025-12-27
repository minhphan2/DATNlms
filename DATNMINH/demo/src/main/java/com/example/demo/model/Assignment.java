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
@Table(name = "assignments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Assignment {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "assignmentid")
    private Integer assignmentId;
    

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid", nullable = false)
    private Course course;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "Instructions", nullable = false, columnDefinition = "TEXT")
    private String instructions;

     @Column(name = "attachmenturl") 
    private String attachmentUrl;

    @Column(name = "deadline", nullable = false)
    private LocalDateTime deadline;

    @Column(name = "maxscore", nullable = false)
    private Integer maxScore;

    @CreationTimestamp
    @Column(name = "createdat")
    private LocalDateTime createdAt;

}
