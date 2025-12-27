package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.example.demo.Service.Service.QuestionService;
import com.example.demo.DTO.QuestionResponse.QuestionResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.DTO.QuestionRequest.CreateRequest;
import com.example.demo.DTO.QuestionRequest.UpdateRequest;

import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/questions")
@RequiredArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    @GetMapping
    public ResponseEntity<List<QuestionResponse>> getAllQuestions(){
        return ResponseEntity.ok(questionService.findAll());
    }

    @PostMapping
    public ResponseEntity<QuestionResponse> createQuestion(@RequestBody CreateRequest request){
        QuestionResponse questionResponse = questionService.createQuestion(request);
        return ResponseEntity.ok(questionResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuestionResponse> updateQuestion(@PathVariable Integer id, @RequestBody UpdateRequest request){
        QuestionResponse questionResponse = questionService.updateQuestion(id,request);
        return ResponseEntity.ok(questionResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuestion(@PathVariable Integer id){
        questionService.deleteQuestion(id);
        return ResponseEntity.noContent().build();
    }
}
