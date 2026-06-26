package com.example.scheduleproject.dto.Comment;

import lombok.Getter;

@Getter
public class CreateCommentRequest {

    private Long scheduleId;
    private String contents;
    private String authorName;
    private String pw;
}
