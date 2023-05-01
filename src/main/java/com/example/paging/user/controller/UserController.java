package com.example.paging.user.controller;

import com.example.paging.user.dto.UserDto;
import com.example.paging.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserRepository userRepository;

    // 나이로 유저 조회
    @GetMapping("/users")
    public List<UserDto> findByAge(@RequestParam Long age, Pageable pageable) {
        // return userRepository.findByNickname(nickname, pageable);
        return userRepository.findByAge(age, pageable)
                .map(UserDto::from).getContent();
    }
}