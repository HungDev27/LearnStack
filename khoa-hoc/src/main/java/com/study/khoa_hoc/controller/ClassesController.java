package com.study.khoa_hoc.controller;


import com.study.khoa_hoc.dto.ApiResponse;
import com.study.khoa_hoc.dto.request.ClassesRequest;
import com.study.khoa_hoc.dto.response.ClassesDetailResponse;
import com.study.khoa_hoc.dto.response.ClassesResponse;
import com.study.khoa_hoc.service.IClassesService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/classes")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class ClassesController {

    IClassesService classesService;

    @GetMapping
    public ResponseEntity<?> getAllClasse() {
        List<ClassesResponse> classesServiceALl = classesService.findALl();
        return ResponseEntity.ok(ApiResponse.builder()
                .data(classesServiceALl)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getById(@PathVariable Long id) {
        ClassesDetailResponse classesDetailRes = classesService.findById(id);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(classesDetailRes)
                .build());
    }

    @PostMapping
    public ResponseEntity<?> addClass(@Valid @RequestBody ClassesRequest classesRequest) {
        ClassesResponse classesResponse = classesService.save(classesRequest);
        return ResponseEntity.ok(ApiResponse.<ClassesResponse>builder().data(classesResponse).build());
    }

    @PutMapping
    public  ResponseEntity<?> updateClass(@PathVariable Long id,@Valid @RequestBody ClassesRequest classesRequest){
        ClassesResponse classesResponse = classesService.update(id, classesRequest);
        return ResponseEntity.ok(ApiResponse.builder().data(classesResponse).build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id){
        classesService.delete(id);
    }
}
