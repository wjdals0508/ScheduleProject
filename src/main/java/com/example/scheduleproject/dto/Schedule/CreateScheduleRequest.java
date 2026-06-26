package com.example.scheduleproject.dto.Schedule;

import lombok.Getter;

@Getter
public class CreateScheduleRequest {

    private String title;
    private String contents;
    private String authorName;
    private String pw;
}
