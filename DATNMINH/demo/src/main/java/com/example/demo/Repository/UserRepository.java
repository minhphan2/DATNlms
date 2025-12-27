package com.example.demo.Repository;


import com.example.demo.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
//khi nao can tim theo trang thi dung query
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User>findByFullname(String fullname);
    Optional<User>findByEmail(String email);
    List<User>findByRole(User.Role role);
    List<User>findAll();
    boolean existsByEmail(String email);
}
