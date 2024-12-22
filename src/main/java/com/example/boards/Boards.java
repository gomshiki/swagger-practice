package com.example.boards;

import lombok.Getter;

@Getter
public class Boards {
    private String title;
    private String contents;

    public Boards(String title, String contents) {
        this.title = title;
        this.contents = contents;
    }
}
