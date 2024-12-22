package com.example.boards;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 응답DTO")
public record BoardResponse(
        @Schema(description = "게시글 ID", example = "1")
        Long savedId,
        @Schema(description = "게시글 제목", example = "너드커넥션 노래중 최애곡은?")
        String title,
        @Schema(description = "게시글 내용", example = "좋은밤 좋은꿈!")
        String contents
) {
    public static BoardResponse of(Long savedId,Boards boards) {
        return new BoardResponse(savedId, boards.getTitle(), boards.getContents());
    }
}
