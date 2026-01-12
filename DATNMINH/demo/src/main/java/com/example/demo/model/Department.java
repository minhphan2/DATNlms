package com.example.demo.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;



import java.time.LocalDateTime;


@Entity
@Table(name = "departments")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Department {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    @Column(name = "departmentid", nullable = false, unique = true)
    private Integer id;

    @Column(name = "name", length = 200, nullable = false)
    private String name;
    
}
