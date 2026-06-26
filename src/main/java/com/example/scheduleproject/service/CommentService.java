package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.Comment.CreateCommentRequest;
import com.example.scheduleproject.dto.Comment.CreateCommentResponse;
import com.example.scheduleproject.entity.Comment;
import com.example.scheduleproject.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    @Transactional
    public CreateCommentResponse save(CreateCommentRequest request) {

        int commentCount = commentRepository.findByScheduleId(request.getScheduleId()).size();
        if (commentCount >= 10) {
            throw new IllegalStateException("더 이상 댓글을 달 수 없습니다.");
        }

        Comment comment = new Comment(
                request.getScheduleId(),
                request.getContents(),
                request.getAuthorName(),
                request.getPw()
        );

        Comment savedComment = commentRepository.save(comment);

        return new CreateCommentResponse(
                savedComment.getScheduleId(),
                savedComment.getId(),
                savedComment.getContents(),
                savedComment.getAuthorName(),
                savedComment.getCreatedAt(),
                savedComment.getModifiedAt()
        );
    }








}
