package com.example.paging.user.dto;

import com.example.paging.user.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter
@AllArgsConstructor
// builder는 @RequiredArgsConstructor 말고 @AllArgsConstructor 써야 함.
// @RequiredArgsConstructor는 초기화 되지 않은 final 필드와 @NonNull 어노테이션이 붙은 필드에 대한 생성자 생성
@Builder
public class UserDto {
    private Long userId;
    private String password;
    private String email;
    private String nickname;

    private Long age;

    public UserDto(User user) {
        this.userId = user.getUserId();
        this.password = user.getPassword();
        this.email = user.getEmail();
        this.nickname = user.getNickname();
        this.age = user.getAge();
    }

    public static UserDto from(User user) {
        return UserDto.builder()
                .userId(user.getUserId())
                .password(user.getPassword())
                .email(user.getEmail())
                .nickname(user.getNickname())
                .age(user.getAge())
                .build();
    }
}