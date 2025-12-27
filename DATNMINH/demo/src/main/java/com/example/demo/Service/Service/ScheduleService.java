package com.example.demo.Service.Service;


import java.util.List;
import com.example.demo.DTO.ScheduleRequest.CreateRequest;
import com.example.demo.DTO.ScheduleRequest.UpdateRequest;
import com.example.demo.DTO.ScheduleResponse.ScheduleResponse;

public interface ScheduleService {
    ScheduleResponse createSchedule(CreateRequest request);//

    ScheduleResponse updateSchedule(Integer id, UpdateRequest request);//

    void deleteSchedule(Integer id);

    ScheduleResponse findById(Integer id);

    List<ScheduleResponse> findAll();//

    List<ScheduleResponse> findByCourseId(Integer courseId);//
    
}
