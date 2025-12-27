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
@Table(name = "users")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userid")
    private Integer id;

    @Column(name = "fullname", length = 100)
    private String fullname;

    @Column(name = "email", unique = true , length = 100)
    private String email;

    @Column(name = "password")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(name = "role" , nullable = false)
    private Role role;

    @Column(name = "phone", nullable = false , unique = true)
    private String phone;

    public enum Role{
        admin,
        teacher,
        student
    }

    @Column(name = "avatar", nullable = true)
    private String avatar;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private Status status;

    public enum Status{
        inactive,
        active
    } 


    @CreationTimestamp
    @Column(name = "createdat")
    private LocalDateTime createdat;

    @OneToMany(mappedBy = "teacher", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Course> courses;


    @OneToMany(mappedBy = "student", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Enrollment> enrollments;

    @Override
    public String toString(){
        return "";
    }

}
