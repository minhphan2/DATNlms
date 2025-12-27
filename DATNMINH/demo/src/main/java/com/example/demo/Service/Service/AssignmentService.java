package com.example.demo.Service.Service;

import org.springframework.web.multipart.MultipartFile;
import java.util.List;
import com.example.demo.DTO.AssignmentRequest.CreateRequest;
import com.example.demo.DTO.AssignmentRequest.UpdateRequest;
import com.example.demo.DTO.AssignmentResponse.AssignmentResponse;

public interface AssignmentService {
    AssignmentResponse createAssignment(CreateRequest request);

    AssignmentResponse updateAssignment(Integer id, UpdateRequest request);

    void deleteAssignment(Integer id);

    AssignmentResponse findById(Integer id);//

    List<AssignmentResponse> findAll();//

    List<AssignmentResponse> findByCourseId(Integer courseId);//

    AssignmentResponse uploadAttachment(Integer id, MultipartFile file);

    void deleteAttachment(Integer id);


    byte[] downloadAttachment(Integer id);

   
}