package com.study.khoa_hoc.controller;

import com.study.khoa_hoc.dto.ApiResponse;
import com.study.khoa_hoc.dto.request.CourseRequest;
import com.study.khoa_hoc.dto.request.TeacherRequest;
import com.study.khoa_hoc.dto.response.CourseResponse;
import com.study.khoa_hoc.dto.response.TeacherResponse;
import com.study.khoa_hoc.service.ICourseService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/courses")
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class CourseController {

    ICourseService courseService;

    @GetMapping
    public ResponseEntity<?> getAll() {
        return ResponseEntity.ok(
                ApiResponse.builder()
                .data(courseService.findAll())
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getByID(@PathVariable Long id) {
        return ResponseEntity.ok(
                ApiResponse.builder()
                        .data(courseService.findById(id))
                        .build());
    }

    @PostMapping
    public ResponseEntity<?> addCourse(@Valid @RequestBody CourseRequest courseRequest){
        CourseResponse courseResponse = courseService.save(courseRequest);
        return ResponseEntity.ok(
                ApiResponse.<CourseResponse>builder()
                        .data(courseResponse)
                        .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody CourseRequest courseRequest) {
        CourseResponse courseResponse = courseService.updateById(id,courseRequest);
        return ResponseEntity.ok(ApiResponse.builder()
                .data(courseResponse)
                .build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        courseService.delete(id);
    }

}
