package com.example.scheduleproject.controller;


import com.example.scheduleproject.dto.Schedule.*;
import com.example.scheduleproject.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<CreateScheduleResponse> create(
            @RequestBody CreateScheduleRequest request) {
        CreateScheduleResponse result = scheduleService.save(request);
        return ResponseEntity.status(HttpStatus.CREATED).body(result);
    }

    @GetMapping("/schedules/{id}")
    public ResponseEntity<GetScheduleResponse> getOne(
            @PathVariable Long id) {
        GetScheduleResponse result = scheduleService.getOne(id);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<GetScheduleResponse>> getAll(
            @RequestParam(required = false) String authorName) {
        List<GetScheduleResponse> result = scheduleService.getAll(authorName);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @PutMapping("/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponse> update(
            @PathVariable Long id,
            @RequestBody UpdateScheduleRequest request) {
        UpdateScheduleResponse result = scheduleService.update(id, request);
        return ResponseEntity.status(HttpStatus.OK).body(result);
    }

    @DeleteMapping("/schedules/{id}")
    public ResponseEntity<UpdateScheduleResponse> delete(
            @PathVariable Long id,
            @RequestBody DeleteScheduleRequest request) {
        scheduleService.delete(id, request);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
