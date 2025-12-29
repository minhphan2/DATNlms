package com.example.demo.Controller;


import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.example.demo.Service.Service.MaterialService;
import com.example.demo.DTO.MaterialRequest.CreateRequest;
import com.example.demo.DTO.MaterialRequest.UpdateRequest;
import com.example.demo.DTO.MaterialResponse.MaterialResponse;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;




@RestController
@RequestMapping("api/materials")
@RequiredArgsConstructor

public class MaterialController {
    private final MaterialService materialService;

    @GetMapping
    public ResponseEntity<List<MaterialResponse>> getAllMaterials(){
        return ResponseEntity.ok(materialService.findAll());
    }

    @GetMapping("/bylesson/{lessonId}")
    public ResponseEntity<List<MaterialResponse>> getMaterialsByLessonId(@PathVariable Integer lessonId)
    {
        return ResponseEntity.ok(materialService.getMaterialsByLessonId(lessonId));
    }
    
    @PostMapping("/upload")
    public ResponseEntity<MaterialResponse> uploadMaterial(
        @RequestParam("lessonId") Integer lessonId,
        @RequestParam("file") MultipartFile file
    ){
        return ResponseEntity.ok(materialService.uploadMaterial(lessonId, file));
    }

    @PostMapping("/upload-multiple")
    public ResponseEntity<List<MaterialResponse>> uploadMultipleMaterials(
        @RequestParam("lessonId") Integer lessonId,
        @RequestParam("files") List<MultipartFile> files
    ) {
        return ResponseEntity.ok(materialService.uploadMultipleMaterials(lessonId, files));
    }

    @GetMapping("/download/{id}")
    public ResponseEntity<byte[]> downloadMaterial(@PathVariable Integer id) {
        MaterialResponse materialResponse = materialService.findById(id);
        byte[] fileContent = materialService.downloadMaterial(id);
        
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + materialResponse.getFileName() + "\"")
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(fileContent);
    }

    @PutMapping("/{id}")
    public ResponseEntity<MaterialResponse> updateMaterial(@PathVariable Integer id, @RequestBody UpdateRequest request){
        MaterialResponse materialResponse = materialService.updateMaterial(id, request);
        return ResponseEntity.ok(materialResponse);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMaterial(@PathVariable Integer id){
        materialService.deleteMaterial(id);
        return ResponseEntity.noContent().build();
    }
    
}
