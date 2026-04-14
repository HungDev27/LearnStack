package com.study.khoa_hoc.controller;

import com.study.khoa_hoc.dto.ApiResponse;
import com.study.khoa_hoc.dto.request.TeacherRequest;
import com.study.khoa_hoc.dto.response.TeacherResponse;
import com.study.khoa_hoc.service.ITeacherService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/teachers")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherController {

    ITeacherService teacherService;

    @GetMapping
    public ResponseEntity<?> findALl(){
        List<TeacherResponse> teacherResponses = teacherService.findAll();
        return ResponseEntity.ok(teacherResponses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id){
        return ResponseEntity.ok(ApiResponse.<TeacherResponse>builder()
                .data(teacherService.findById(id))
                .build());
    }

    @PostMapping
    public ResponseEntity<?> createTeacher(@Valid @RequestBody TeacherRequest teacherRequest){
        TeacherResponse teacherResponse = teacherService.save(teacherRequest);
        return ResponseEntity.ok(ApiResponse.<TeacherResponse>builder()
                .data(teacherResponse)
                .build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody TeacherRequest teacherRequest) {
        TeacherResponse teacherResponse = teacherService.updateById(id,teacherRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(teacherResponse)
                .build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        teacherService.deleteById(id);
    }
}
