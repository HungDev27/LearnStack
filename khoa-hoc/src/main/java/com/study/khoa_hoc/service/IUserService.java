package com.study.khoa_hoc.service;

import com.study.khoa_hoc.dto.request.UserRequest;
import com.study.khoa_hoc.dto.request.UserUpdateRequest;
import com.study.khoa_hoc.dto.response.UserResponse;

import java.util.List;

public interface IUserService {

    List<UserResponse> findAll();

    List<UserResponse> findAllOne();

    UserResponse findById(Long id);

    UserResponse save(UserRequest userRequest);

    UserResponse updateById(Long id, UserUpdateRequest userRequest);

    void deleteById(Long id);
}
