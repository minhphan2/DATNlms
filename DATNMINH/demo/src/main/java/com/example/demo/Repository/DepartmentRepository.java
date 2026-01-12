package com.example.demo.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
import com.example.demo.model.Department;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {
    Optional<Department> findByName(String name);
}
