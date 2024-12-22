package com.example.users;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "유저 등록 요청")
public record RegisterRequest(
        @Schema(description = "이메일", example = "example@email.com")
        String email,
        @Schema(description = "비밀번호", example = "password")
        String password
) {
    public static Users of(RegisterRequest registerRequest) {
        return Users.builder()
                .email(registerRequest.email)
                .password(registerRequest.password)
                .build();
    }
}
