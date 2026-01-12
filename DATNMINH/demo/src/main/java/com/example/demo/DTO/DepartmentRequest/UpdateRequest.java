package com.example.demo.DTO.DepartmentRequest;
import com.example.demo.model.Department;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder

public class UpdateRequest {
    private Integer id;
    private String name;
}
