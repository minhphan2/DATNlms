package com.example.demo.Controller;




import com.example.demo.Service.Service.SectionService;
import com.example.demo.DTO.SectionResponse.SectionResponse;
import com.example.demo.DTO.SectionRequest.*;

import com.example.demo.Repository.SectionRepository;
import com.example.demo.Service.ServiceImpl.SectionServiceImpl;
import com.example.demo.model.User;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;


@RestController
@RequestMapping("api/sections")
@RequiredArgsConstructor
public class SectionController {
    private final SectionService sectionService;

    @GetMapping("/bycourse/{id}")
    public ResponseEntity<List<SectionResponse>> countByCourseIdList(@PathVariable Integer id){
        return ResponseEntity.ok(sectionService.countByCourseIdList(id));
    }

    @PostMapping
    public ResponseEntity<SectionResponse> createSection(@RequestBody CreateRequest request){
        SectionResponse sectionResponse = sectionService.createSection(request);
        return ResponseEntity.ok(sectionResponse);

    }

    @PutMapping("/{id}")
    public ResponseEntity<SectionResponse> updateSection(@PathVariable Integer id, @RequestBody UpdateRequest request){
        SectionResponse sectionResponse = sectionService.updateSection(id,request);
        return ResponseEntity.ok(sectionResponse);
    }
}
