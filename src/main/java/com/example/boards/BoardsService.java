package com.example.boards;

import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;

@Service
public class BoardsService {
    private static final AtomicLong autoIncrement = new AtomicLong();
    private static final Map<Long, Boards> boardsInMemory = new HashMap<>();

    public BoardResponse saveBoards(BoardRequest boardRequest) {
        Boards board = BoardRequest.of(boardRequest);
        Long savedId = autoIncrement.incrementAndGet();
        boardsInMemory.put(savedId, board);
        return BoardResponse.of(savedId, board);
    }

    public BoardResponse getBoard(Long id) {
        Boards boards = boardsInMemory.get(id);
        return BoardResponse.of(id, boards);
    }
}
