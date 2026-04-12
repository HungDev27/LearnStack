package com.study.khoa_hoc.service.ipml;

import com.study.khoa_hoc.dto.request.UserRequest;
import com.study.khoa_hoc.dto.request.UserUpdateRequest;
import com.study.khoa_hoc.dto.response.UserResponse;
import com.study.khoa_hoc.entity.User;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.mapper.UserMapper;
import com.study.khoa_hoc.repository.UserRepository;
import com.study.khoa_hoc.service.IUserService;
import jakarta.transaction.Transactional;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class UserService implements IUserService {

    UserRepository userRepository;
    // IUserMapper userMapper;
    UserMapper userMapper;

    @Override
    public List<UserResponse> findAll() { // N+1 query problem
        List<User> users = userRepository.findAll();
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public List<UserResponse> findAllOne() {// query 1 lần
        List<User> users = userRepository.findAllWithProfile();
        return users.stream().map(userMapper::toDto).toList();
    }

    @Override
    public UserResponse findById(Long id) {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse save(UserRequest userRequest) {
        User user = userMapper.toEntity(userRequest);
        userRepository.save(user);
        return userMapper.toDto(user);
    }

    @Override
    public UserResponse updateById(Long id, UserUpdateRequest userRequest) {
        User user = userRepository.findById(id).orElseThrow(() -> new ApiException(ErrorCode.USER_NOT_EXIST));
        userMapper.updateEntity(user, userRequest);
        User saved = userRepository.save(user);
        return userMapper.toDto(saved);
    }

    @Override
    @Transactional
    public void deleteById(Long id) {
        userRepository.deleteById(id);
    }

}
