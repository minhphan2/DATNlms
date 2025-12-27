package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;



import java.time.LocalDateTime;

@Entity
@Table(name = "courses")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Course {
    

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "courseid", nullable = false, unique = true)
    private Integer id;


    @Column(name = "coursename", length = 200, nullable = false)
    private String courseName;

    @Column(name = "title", length = 200, nullable = false)
    private String title;



    @Column(name = "description", length = 500)
    private String description;

    @Column(name = "category",length = 100)
    private String category;

    
    
    @Column(name = "thumbnail", length = 300)
    private String thumbnail;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;
    public enum Status{
        inactive,
        active
    }

    @Column(name = "maxstudents")  
    private Integer maxStudents;

    @CreationTimestamp
    @Column(name = "createdat")
    private LocalDateTime createdAt;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "teacherid", nullable = false)
    private User teacher;

    @Column(name = "startdate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "enddate", nullable = false)
    private LocalDateTime endDate;

}
