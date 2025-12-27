package com.example.demo.Controller;



import com.example.demo.DTO.LessonRequest.CreateRequest;
import com.example.demo.DTO.LessonRequest.UpdateRequest;
import com.example.demo.DTO.LessonResponse.LessonResponse;
import com.example.demo.Repository.LessonRepository;
import com.example.demo.Service.ServiceImpl.LessonServiceImpl;
import com.example.demo.model.Lesson;
import com.example.demo.Service.Service.LessonService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;





@RestController
@RequestMapping("api/lessons")
@RequiredArgsConstructor
public class LessonController {
    private final LessonRepository lessonRepository;
    private final LessonService lessonService;



    @GetMapping
    public ResponseEntity<List<LessonResponse>> getAllLessons(){
        return ResponseEntity.ok(lessonService.findAll());
    }


    @PostMapping
    public ResponseEntity<LessonResponse> createLesson(@RequestBody CreateRequest request){
        LessonResponse lessonResponse = lessonService.createLesson(request);
        return ResponseEntity.ok(lessonResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LessonResponse> updateLesson(@PathVariable Integer id, @RequestBody UpdateRequest request){
        LessonResponse lessonResponse = lessonService.updateLesson(id,request);
        return ResponseEntity.ok(lessonResponse);
    }
}
