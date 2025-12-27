package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.example.demo.Service.Service.QuizzesService;
import com.example.demo.DTO.QuizzesResponse.QuizzesResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.DTO.QuizzesRequest.CreateRequest;
import com.example.demo.DTO.QuizzesResponse.QuizzesResponse;
import com.example.demo.DTO.QuizzesRequest.UpdateRequest;
import com.example.demo.Service.Service.QuizzesService;
import java.util.List;

import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.PathVariable;




@RestController
@RequestMapping("api/quizzes")
@RequiredArgsConstructor
public class QuizzesController {
    private final QuizzesService quizzesService;
    
    @GetMapping
    public ResponseEntity<List<QuizzesResponse>> getAllQuizzes(){
        return ResponseEntity.ok(quizzesService.findAll());
    }

    @PostMapping
    public ResponseEntity<QuizzesResponse> createQuizzes(@RequestBody CreateRequest request){
        QuizzesResponse quizzesResponse = quizzesService.createQuizzes(request);
        return ResponseEntity.ok(quizzesResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<QuizzesResponse> updateQuizzes(@PathVariable Integer id, @RequestBody UpdateRequest request){
        QuizzesResponse quizzesResponse = quizzesService.updateQuizzes(id,request);
        return ResponseEntity.ok(quizzesResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteQuizzes(@PathVariable Integer id){
        quizzesService.deleteQuizzes(id);
        return ResponseEntity.noContent().build();
    }
    
    
    
}
