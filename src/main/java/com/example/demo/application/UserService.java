package com.example.demo.application;

import com.example.demo.domain.User;
import com.example.demo.infrastructure.persistence.UserRepository;
import com.example.demo.application.dto.UserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.Errors;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder; // 변경된 부분

    @Transactional
    public void userJoin(UserDto.Request dto) {
        dto.setPassword(passwordEncoder.encode(dto.getPassword())); // 변경된 부분
        userRepository.save(dto.toEntity());
    }

    @Transactional(readOnly = true)
    public Map<String, String> validateHandling(Errors errors) {
        Map<String, String> validatorResult = new HashMap<>();
        for (FieldError error : errors.getFieldErrors()) {
            String validKeyName = String.format("valid_%s", error.getField());
            validatorResult.put(validKeyName, error.getDefaultMessage());
        }
        return validatorResult;
    }

    /* 회원수정 (dirty checking) */
    @Transactional
    public void modify(UserDto.Request dto) {
        User user = userRepository.findById(dto.getId()).orElseThrow(() ->
                new IllegalArgumentException("해당 회원이 존재하지 않습니다."));
        String encPassword = passwordEncoder.encode(dto.getPassword()); // 변경된 부분
        user.modify(dto.getNickname(), encPassword);
    }
}
