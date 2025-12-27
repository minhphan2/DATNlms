package com.example.demo.Repository;

import com.example.demo.model.Course;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//khi nao can tim theo trang thi dung query
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CourseRepository extends JpaRepository<Course,Integer> {
    List<Course> findByTitle(String title);
    Optional<Course> findById(Integer id);
    List<Course> findAll();

}
