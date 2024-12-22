package com.example.users;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@Tag(
        name = "users-controller",
        description = "유저 등록 및 로그인을 위한 API 입니다."
)
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    @Operation(summary = "유저 등록 API", description = "유저를 등록하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = Long.class)
                    )),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST"),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @PostMapping
    public ResponseEntity<?> register(RegisterRequest registerRequest) {
        Long savedId = userService.register(registerRequest);
        return ResponseEntity.ok(Map.of("유저 ID", savedId));
    }

    @Operation(summary = "유저 로그인 API", description = "유저를 로그인하는 API 입니다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "로그인 성공", summary = "로그인 성공", name = "로그인 성공")
                    )),
            @ApiResponse(responseCode = "400", description = "BAD REQUEST",
                    content = @Content(
                            schema = @Schema(implementation = String.class),
                            examples = @ExampleObject(value = "로그인 실패", summary = "로그인 실패", name = "로그인 실패")
                    )),
            @ApiResponse(responseCode = "500", description = "INTERNAL SERVER ERROR")
    })
    @GetMapping
    public ResponseEntity<?> login(LoginRequest loginRequest) {
        Boolean result = userService.login(loginRequest);
        if (!result) {
            return ResponseEntity.badRequest().body("로그인 실패");
        }
        return ResponseEntity.ok("로그인 성공");
    }
}
