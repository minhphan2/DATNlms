package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;


@Entity
@Table(name = "materials")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Material {
 
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "materialid")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessonid", nullable = false)
    @JsonIgnore
    private Lesson lesson;

    @Column(name = "filename", nullable = false)
    private String fileName;

    @Column(name = "filetype", nullable = false)
    private String fileType;

    @Column(name = "fileurl", nullable = false)
    private String fileUrl;

    @CreationTimestamp
    @Column(name = "uploadedat", nullable = false, updatable = false)
    private LocalDateTime uploadedAt;

}
