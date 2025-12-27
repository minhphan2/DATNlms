package com.example.demo.Service.ServiceImpl;

import org.springframework.stereotype.Service;
import com.example.demo.Repository.AssignmentRepository;
import com.example.demo.Service.Service.AssignmentService;
import com.example.demo.mapper.AssignmentMapper;
import com.example.demo.Repository.CourseRepository;
import com.example.demo.DTO.AssignmentRequest.CreateRequest;
import com.example.demo.DTO.AssignmentRequest.UpdateRequest;
import com.example.demo.DTO.AssignmentResponse.AssignmentResponse;

import org.springframework.web.multipart.MultipartFile;
import com.example.demo.model.Assignment;
import com.example.demo.Service.Service.FileStorageService;



import com.example.demo.model.Course;
import lombok.RequiredArgsConstructor;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AssignmentServiceImpl implements AssignmentService {
    
    private final AssignmentRepository assignmentRepository;
    private final CourseRepository courseRepository;
    private final AssignmentMapper assignmentMapper;
    private final FileStorageService fileStorageService;


    @Override
    public AssignmentResponse createAssignment(CreateRequest request)
    {
        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("khong tim thay course"));

        Assignment assignment = assignmentMapper.toEntity(request, course);
        Assignment savedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toResponse(savedAssignment);
    }

    @Override
    public AssignmentResponse updateAssignment(Integer id ,UpdateRequest request)
    {
        Assignment assignment = assignmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay assignment"));

        Course course = courseRepository.findById(request.getCourseId())
        .orElseThrow(() -> new RuntimeException("khong tim thay course"));

        assignmentMapper.updateEntity(assignment, request, course);
        Assignment updatedAssignment = assignmentRepository.save(assignment);
        return assignmentMapper.toResponse(updatedAssignment);
    }

    @Override
    public void deleteAssignment(Integer id)
    {
        assignmentRepository.deleteById(id);
    }

    @Override
    public AssignmentResponse findById(Integer id)
    {
        Assignment assignment = assignmentRepository.findById(id)
       .orElseThrow(() -> new RuntimeException("khong tim thay assignment"));
        return assignmentMapper.toResponse(assignment);

    }

    @Override
    public List<AssignmentResponse> findAll(){
        return assignmentRepository.findAll().stream()
        .map(assignmentMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public List<AssignmentResponse> findByCourseId(Integer courseId){
        return assignmentRepository.findByCourseId(courseId).stream()
        .map(assignmentMapper::toResponse)
        .collect(Collectors.toList());
    }

    @Override
    public AssignmentResponse uploadAttachment(Integer id, MultipartFile file){
        Assignment assingment = assignmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay assignment"));


        if(assingment.getAttachmentUrl() != null){
            fileStorageService.deleteFile(assingment.getAttachmentUrl());
        }

        String fireUrl = fileStorageService.storeFile(file, "assignments");
        assingment.setAttachmentUrl(fireUrl);

        Assignment updatedAssignment = assignmentRepository.save(assingment);
        return assignmentMapper.toResponse(updatedAssignment);
    }

    @Override
    public void deleteAttachment(Integer id){
        Assignment assingment = assignmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay assignment"));

        if(assingment.getAttachmentUrl() != null){
            fileStorageService.deleteFile(assingment.getAttachmentUrl());
            assingment.setAttachmentUrl(null);
            assignmentRepository.save(assingment);
        }
    } 
    
    @Override
    public byte[] downloadAttachment(Integer id){
        Assignment assignment = assignmentRepository.findById(id)
        .orElseThrow(() -> new RuntimeException("khong tim thay assignment"));

        if(assignment.getAttachmentUrl() == null){
            throw new RuntimeException("khong co file de tai ve");
        }

        return fileStorageService.loadFile(assignment.getAttachmentUrl());
    }


}
