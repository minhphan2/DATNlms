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
@Table(name = "questions")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "questionid")
    private Integer questionid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quizid", nullable = false)
    @JsonIgnore
    private Quizzes quiz;

    @Column(name = "questiontext", nullable = false, columnDefinition = "TEXT")
    private String questionText;

    @Column(name = "type", nullable = false)
    private QuestionType type;


    public enum QuestionType {
       single,
       multiple,
    }
}
