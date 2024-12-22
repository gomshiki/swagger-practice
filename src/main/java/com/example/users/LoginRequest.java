package com.example.users;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저 로그인 요청")
public record LoginRequest(
        @Schema(description = "이메일", example = "example@email.com")
        String email,
        @Schema(description = "비밀번호", example = "password")
        String password)
{
    public static Users of(LoginRequest loginRequest) {
        return Users.builder()
                .email(loginRequest.email)
                .password(loginRequest.password)
                .build();
    }
}
