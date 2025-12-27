package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;



@Entity
@Table(name = "answers")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Answer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "answerid")
    private Integer answerid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "questionid", nullable = false)
    @JsonIgnore
    private Question question;

    @Column(name = "answertext", columnDefinition = "TEXT")
    private String answerText;

    @Column(name = "iscorrect", nullable = false)
    private Boolean isCorrect;

}
