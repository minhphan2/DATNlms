package com.example.demo.DTO.LessonRequest;



import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.Builder;
import com.example.demo.model.Lesson;

import jakarta.validation.constraints.*;



@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CreateRequest {  

    @NotNull(message = "khong duoc de trong section")
    private Integer sectionId;

    @NotNull(message = "khong duoc de trong title")
    private String title;

    @NotNull(message = "khong duoc de trong content")
    private String content;


    private String videoUrl;

    @NotNull(message = "khong duoc de trong position")
    private Integer position;


}
