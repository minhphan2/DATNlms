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
@Table(name = "quizzes")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Quizzes {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "quizid")
    private Integer quizid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid", nullable = false)
    @JsonIgnore
    private Course course;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "timelimit", nullable = false)
    private Integer timeLimit; // in minutes

    @CreationTimestamp
    @Column(name = "createdat", nullable = false)
    private LocalDateTime createdAt;
    
}
