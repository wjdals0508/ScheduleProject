package com.example.scheduleproject.controller;

import com.example.scheduleproject.dto.Comment.CreateCommentRequest;
import com.example.scheduleproject.dto.Comment.CreateCommentResponse;
import com.example.scheduleproject.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/comments")
    public ResponseEntity<CreateCommentResponse> create(
            @RequestBody CreateCommentRequest request) {
        CreateCommentResponse result = commentService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }




}
