package com.study.khoa_hoc.controller;

import com.study.khoa_hoc.dto.ApiResponse;
import com.study.khoa_hoc.dto.request.UserRequest;
import com.study.khoa_hoc.dto.response.UserResponse;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.service.IUserService;
import jakarta.validation.Valid;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/users")
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserController {
    IUserService userService;

//    @GetMapping
//    public ResponseEntity<?> getAll(){
//        List<UserResponse> userResponses =  userService.findAll();
//        return ResponseEntity.ok(userResponses);
//    }
    @GetMapping
    public ResponseEntity<?> getAll() {
        List<UserResponse> userResponses = userService.findAllOne();
        return ResponseEntity.ok(userResponses);
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse<List<UserResponse>>> getAllWithProfile() {
        List<UserResponse> userResponses = userService.findAllOne();
        return ResponseEntity.ok(ApiResponse.<List<UserResponse>>builder()
                .data(userResponses)
                .build());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse<UserResponse>> getById(@PathVariable Long id) {
        UserResponse userResponse = userService.findById(id);
        // return ResponseEntity.ok(new ApiResponse<>(2000, "Tìm thấy user",userResponse));
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .data(userResponse)
                .build());
    }

    @PostMapping
    public ResponseEntity<?> save(@Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.save(userRequest);
       // return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .data(userResponse)
                .build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@PathVariable Long id, @Valid @RequestBody UserRequest userRequest) {
        UserResponse userResponse = userService.updateById(id, userRequest);
        //return ResponseEntity.status(HttpStatus.OK).body(userResponse);
        return ResponseEntity.ok(ApiResponse.<UserResponse>builder()
                .data(userResponse)
                .build());
    }


    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        userService.deleteById(id);
    }
}
