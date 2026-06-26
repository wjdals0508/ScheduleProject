package com.example.scheduleproject.dto.Schedule;

import com.example.scheduleproject.dto.Comment.GetCommentResponse;
import com.example.scheduleproject.entity.Comment;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetScheduleResponse {

    private final Long id;
    private final String title;
    private final String contents;
    private final String authorName;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;

    private final List<GetCommentResponse> commentList;

    public GetScheduleResponse(Long id, String title, String contents, String authorName, List<GetCommentResponse> commentList, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.contents = contents;
        this.authorName = authorName;
        this.commentList = commentList;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }
}
