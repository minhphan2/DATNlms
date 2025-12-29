package com.example.demo.Service.Service;


import java.util.List;


import org.springframework.stereotype.Service;
import com.example.demo.DTO.SectionRequest.CreateRequest;
import com.example.demo.DTO.SectionRequest.UpdateRequest;
import com.example.demo.DTO.SectionResponse.SectionResponse;


@Service
public interface SectionService {
    List<SectionResponse>countByCourseIdList(Integer Id);
    SectionResponse createSection(CreateRequest request);
    SectionResponse updateSection(Integer id , UpdateRequest request);

    List<SectionResponse> findbyCourseId (Integer id);
}
