package com.example.scheduleproject.entity;

import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "comments")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column
    private Long scheduleId;

    @Column(length = 100, nullable = false)
    private String contents;

    @Column(length = 12, nullable = false)
    private String authorName;

    @Column(length = 60, nullable = false)
    private String pw;

    public Comment(Long scheduleId, String contents, String authorName, String pw) {

        if (scheduleId == 0 || contents.isEmpty() || authorName.isEmpty() || pw.isEmpty()) {
            throw new IllegalArgumentException("유효하지 않은 요청 입니다.");
        }

        this.scheduleId = scheduleId;
        this.contents = contents;
        this.authorName = authorName;
        this.pw = pw;
    }
}
