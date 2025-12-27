package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import com.fasterxml.jackson.annotation.JsonIgnore;
import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Schedules {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "scheduleid")
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "courseid", nullable = false)
    @JsonIgnore
    private Course course;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sectionid", nullable = false)
    @JsonIgnore
    private Section section;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lessonid", nullable = false)
    @JsonIgnore
    private Lesson lesson;

    @Column(name = "dayofweek", nullable = false)
    private DOWeek dayOfWeek;

    public enum  DOWeek{
        Mon,
        Tue,
        Wed,
        Thu,
        Fri,
        Sat,
        Sun
    }

    @Column(name = "starttime", nullable = false)
    private String startTime;

    @Column(name = "endtime", nullable = false)
    private String endTime;

    @Column(name = "location", nullable = false)
    private String location;

    @Column(name = "startdate", nullable = false)
    private LocalDateTime startDate;

    @Column(name = "enddate", nullable = false)
    private LocalDateTime endDate;

    @Column(name = "type", nullable = false)
    private type type;

    public enum type {
      online,
      offline
    }
}
