package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import lombok.RequiredArgsConstructor;
import com.example.demo.Service.Service.AnswerService;
import com.example.demo.DTO.AnswerResponse.AnswerResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.example.demo.DTO.AnswerRequest.CreateRequest;
import com.example.demo.DTO.AnswerRequest.UpdateRequest;
import java.util.List;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.RequestParam;




@RestController
@RequestMapping("api/answers")
@RequiredArgsConstructor
public class AnswerController {
    private final AnswerService answerService;

    @GetMapping
    public ResponseEntity<List<AnswerResponse>> getAllAnswers(){
        return ResponseEntity.ok(answerService.findAll());
    }

    @PostMapping
    public ResponseEntity<AnswerResponse> createAnswer(@RequestBody CreateRequest request){
        AnswerResponse answerResponse = answerService.createAnswer(request);
        return ResponseEntity.ok(answerResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AnswerResponse> updateAnswer(@PathVariable Integer id, @RequestBody
        UpdateRequest request){
            AnswerResponse answerResponse = answerService.updateAnswer(id,request);
            return ResponseEntity.ok(answerResponse);
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAnswer(@PathVariable Integer id){
        answerService.deleteAnswer(id);
        return ResponseEntity.noContent().build();
    }
}
