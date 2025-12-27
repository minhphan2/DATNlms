package com.example.demo.Controller;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.demo.Service.Service.ScheduleService;
import com.example.demo.DTO.ScheduleRequest.CreateRequest;
import com.example.demo.DTO.ScheduleRequest.UpdateRequest;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;



import com.example.demo.DTO.ScheduleResponse.ScheduleResponse;
import org.springframework.http.ResponseEntity;
import java.util.List;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;




@RestController
@RequestMapping("api/schedules")
@RequiredArgsConstructor
public class ScheduleController {
    private final ScheduleService scheduleService;


    @GetMapping
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules(){
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/bycourse/{courseid}")
    public ResponseEntity<List<ScheduleResponse>> getSchedulesByCourseId(@PathVariable Integer courseid){
        return ResponseEntity.ok(scheduleService.findByCourseId(courseid));
    }

    @PostMapping
    public ResponseEntity<ScheduleResponse>createSchedule(@RequestBody CreateRequest request){
        ScheduleResponse scheduleResponse = scheduleService.createSchedule(request);
        return ResponseEntity.ok(scheduleResponse);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ScheduleResponse> updateSchedule(@PathVariable Integer id, @RequestBody UpdateRequest request){
        ScheduleResponse scheduleResponse = scheduleService.updateSchedule(id,request);
        return ResponseEntity.ok(scheduleResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSchedule(@PathVariable Integer id){
        scheduleService.deleteSchedule(id);
        return ResponseEntity.noContent().build();
    }
    
    
}
