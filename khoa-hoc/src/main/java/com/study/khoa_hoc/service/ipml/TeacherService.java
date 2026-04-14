package com.study.khoa_hoc.service.ipml;

import com.study.khoa_hoc.dto.request.TeacherRequest;
import com.study.khoa_hoc.dto.response.TeacherResponse;
import com.study.khoa_hoc.entity.Teacher;
import com.study.khoa_hoc.exception.ApiException;
import com.study.khoa_hoc.exception.ErrorCode;
import com.study.khoa_hoc.mapper.TeacherMapper;
import com.study.khoa_hoc.repository.TeacherRepository;
import com.study.khoa_hoc.service.ITeacherService;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class TeacherService implements ITeacherService {

    TeacherRepository teacherRepository;
    TeacherMapper teacherMapper;

    @Override
    public List<TeacherResponse> findAll() {
        List<Teacher> teachers = teacherRepository.findAll();
        return teachers.stream().map(teacherMapper::toDto).toList();
    }

    @Override
    public TeacherResponse findById(Long id) {
        Teacher teacher = teacherRepository.findById(id).
                orElseThrow(() -> new ApiException(ErrorCode.TEACHER_NOT_EXIST));
        return teacherMapper.toDto(teacher);
    }

    @Override
    public TeacherResponse save(TeacherRequest teacherRequest) {
        Teacher teacher = teacherMapper.toEntity(teacherRequest);
        teacherRepository.save(teacher);
        return teacherMapper.toDto(teacher);
    }

    @Override
    public TeacherResponse updateById(Long id, TeacherRequest teacherRequest) {
        Teacher teacher = teacherRepository.findById(id).
                orElseThrow(() -> new ApiException(ErrorCode.TEACHER_NOT_EXIST));
        if (teacherRepository.existsBySdt(teacherRequest.getSdt())
                && !teacher.getSdt().equals(teacherRequest.getSdt())) {
            throw new ApiException(ErrorCode.PHONE_ALREADY_EXISTS);
        }
        // check email trùng
        if (teacherRepository.existsByEmail(teacherRequest.getEmail())
                && !teacher.getEmail().equals(teacherRequest.getEmail())) {
            throw new ApiException(ErrorCode.EMAIL_ALREADY_EXISTS);
        }

        teacherMapper.updateEntity(teacher, teacherRequest);
        Teacher saved = teacherRepository.save(teacher);
        return teacherMapper.toDto(saved);
    }

    @Override
    public void deleteById(Long id) {
        Teacher teacher = teacherRepository.findById(id)
                .orElseThrow(() -> new ApiException(ErrorCode.TEACHER_NOT_EXIST));

        // check ràng buộc với class
//        if (teacher.getClass() != null && !teacher.getClass().isEmpty()) {
//            throw new ApiException(ErrorCode.TEACHER_HAS_CLASSES);
//        }

        teacherRepository.deleteById(id);
    }
}
