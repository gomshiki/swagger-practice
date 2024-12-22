package com.example.boards;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Tag(
        name = "boards-controller",
        description = "게시글 저장 및 조회를 위한 API 입니다."
)
@RequestMapping("/api/v1/boards")
@RequiredArgsConstructor
@RestController
public class BoardsController {
    private final BoardsService boardsService;

    @Operation(summary = "게시글 저장 API", description = "게시글을 저장하는 API 입니다.")
    @ApiResponse(
            responseCode = "200",
            description = "게시글 저장 성공",
            content = @Content(
                    schema = @Schema(implementation = BoardResponse.class)
            )
    )
    @PostMapping
    public ResponseEntity<?> createBoards(@RequestBody BoardRequest boardReqeust) {
        BoardResponse boardResponse = boardsService.saveBoards(boardReqeust);
        return ResponseEntity.ok(boardResponse);
    }

    @Operation(summary = "게시글 조회 API", description = "게시글을 조회하는 API 입니다.")
    @ApiResponse(responseCode = "200", description = "OK",
            content = @Content(
                    schema = @Schema(implementation = BoardResponse.class)
            ))
    @GetMapping
    public ResponseEntity<?> getBoard(@RequestParam("Id") Long id) {
        BoardResponse boardResponse = boardsService.getBoard(id);
        return ResponseEntity.ok(boardResponse);
    }
}
