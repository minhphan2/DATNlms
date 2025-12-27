package com.example.demo.model;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "sections",
     uniqueConstraints = {
        @UniqueConstraint(
            name = "unique_course_position",
            columnNames = {"courseid", "position"}
        )
    }
)
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Section {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sectionid")
    private Integer id;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid", nullable = false)
    private Course course;

    @Column(name = "title", nullable = false)
    private String title;

    @Column(name = "position", nullable = false)
    private Integer position;

    @Column(name = "startdate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "enddate", nullable = false)
    private LocalDateTime endDate;
}
