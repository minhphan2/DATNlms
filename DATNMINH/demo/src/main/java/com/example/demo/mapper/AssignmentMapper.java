package com.example.demo.mapper;


import org.springframework.stereotype.Component;
import com.example.demo.model.Course;
import com.example.demo.DTO.AssignmentRequest.CreateRequest;
import com.example.demo.DTO.AssignmentRequest.UpdateRequest;
import com.example.demo.DTO.AssignmentResponse.AssignmentResponse;
import com.example.demo.model.Assignment;



@Component
public class AssignmentMapper {
    public AssignmentResponse toResponse(Assignment assignment){
        return AssignmentResponse.builder()
        .assignmentId(assignment.getAssignmentId())
        .courseId(assignment.getCourse().getCourseName())
        .title(assignment.getTitle())
        .instructions(assignment.getInstructions())
        .attachmentUrl(assignment.getAttachmentUrl())
        .downloadUrl(assignment.getAttachmentUrl() != null 
                    ? "/api/assignments/download/" + assignment.getAssignmentId() 
                    : null) 
        .maxScore(assignment.getMaxScore())
        .deadline(assignment.getDeadline())
        .createdAt(assignment.getCreatedAt())
        .build();
    }

    public Assignment toEntity(CreateRequest CreateRequest , Course course){
        return Assignment.builder()
        .course(course)
        .title(CreateRequest.getTitle())
        .instructions(CreateRequest.getInstructions())
        .maxScore(CreateRequest.getMaxScore())
        .deadline(CreateRequest.getDeadline())
        .build();
    }


    public void updateEntity(Assignment assignment,UpdateRequest request, Course course){
        assignment.setCourse(course);
        assignment.setTitle(request.getTitle());
        assignment.setInstructions(request.getInstructions());
        assignment.setMaxScore(request.getMaxScore());
        assignment.setDeadline(request.getDeadline());
    }
}
