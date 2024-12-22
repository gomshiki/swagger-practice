package com.example.boards;

import io.swagger.v3.oas.annotations.media.Schema;

@Schema(description = "게시글 저장 요청")
record BoardRequest(
        @Schema(description = "게시글 제목", example = "너드커넥션 노래중 최애곡은?")
        String title,
        @Schema(description = "게시글 내용", example = "좋은밤 좋은꿈!")
        String contents
) {
    public static Boards of(BoardRequest boardRequest) {
        return new Boards(boardRequest.title(), boardRequest.contents);
    }
}
