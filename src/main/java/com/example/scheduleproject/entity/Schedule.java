package com.example.scheduleproject.entity;


import jakarta.persistence.*;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Entity
@Table(name = "schedules")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Schedule extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 30)
    private String title;

    @Column(length = 200)
    private String contents;

    @Column(length = 12)
    private String authorName;

    @Column(length = 60)
    private String pw;

    public Schedule(String title, String contents, String authorName, String pw) {
        this.title = title;
        this.contents = contents;
        this.authorName = authorName;
        this.pw = pw;
    }

    public void update(String title, String authorName) {
        this.title = title;
        this.authorName = authorName;
    }
}
