package com.example.demo.Repository;

import com.example.demo.model.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Long> {
    List<Material> findAll();
    Optional<Material> findById(Integer id);
} 
