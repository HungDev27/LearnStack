package com.study.khoa_hoc.service.ipml;

import com.study.khoa_hoc.dto.request.UserRequest;
import com.study.khoa_hoc.dto.response.UserResponse;
import com.study.khoa_hoc.entity.Profile;
import com.study.khoa_hoc.entity.User;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.mapper.IUserMapper;
import com.study.khoa_hoc.repository.UserRepository;
import com.study.khoa_hoc.service.IUserService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {

    UserRepository userRepository;
    IUserMapper userMapper;

    @Override
    public List<UserResponse> findAll() { // N+1 query problem
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toResponse).toList();
    }

    @Override
    public List<UserResponse> findAllOne() {// query 1 lần
        List<User> users = userRepository.findAllWithProfile();
        return users.stream().map(userMapper::toResponse).toList();
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        userRepository.save(user);
        return userMapper.toResponse(user);
    }

    @Override
    public UserResponse updateById(Long id, UserRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));
        userMapper.updateFromRequest(userRequest, user);
        User saved = userRepository.save(user);
        return userMapper.toResponse(saved);
    }

    @Override
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
