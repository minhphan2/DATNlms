package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.List;
import java.time.LocalDateTime;


@Entity
@Table(name = "lessons",
        uniqueConstraints = {
            @UniqueConstraint(
                name = "unique_section_position",
                columnNames = {"sectionid", "position"}
            )
        }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Lesson {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "lessonid")
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionid", nullable = false)
    private Section section;


    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "content", columnDefinition = "TEXT")
    private String content;

    @Column(name = "videourl")
    private String videoUrl;

    @Column(name = "position", nullable = false)
    private Integer position;

    @CreationTimestamp
    @Column(name = "createdat", updatable = false)
    private LocalDateTime createdAt;
}
