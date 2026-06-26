package com.example.scheduleproject.service;

import com.example.scheduleproject.dto.*;
import com.example.scheduleproject.entity.Schedule;
import com.example.scheduleproject.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public CreateScheduleResponse save(CreateScheduleRequest request) {
        Schedule schedule = new Schedule(
                request.getTitle(),
                request.getContents(),
                request.getAuthorName(),
                request.getPw()
        );

        Schedule savedSchedule = scheduleRepository.save(schedule);

        return new CreateScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getContents(),
                savedSchedule.getAuthorName(),
                savedSchedule.getCreatedAt(),
                savedSchedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public GetScheduleResponse getOne(Long id) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                ()->new IllegalStateException("존재하지 않는 일정 입니다.")
        );
        return new GetScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt()
        );
    }

    @Transactional(readOnly = true)
    public List<GetScheduleResponse> getAll(String AuthorName) {

        List<Schedule> schedules = new ArrayList<>();
        if (AuthorName.isEmpty()) {
            schedules = scheduleRepository.findAll();
        }
        else {
            schedules = scheduleRepository.findByAuthorName(AuthorName);
        }

        List<GetScheduleResponse> dtos = new ArrayList<>();
        for (Schedule schedule : schedules) {
            GetScheduleResponse dto = new GetScheduleResponse(
                    schedule.getId(),
                    schedule.getTitle(),
                    schedule.getContents(),
                    schedule.getAuthorName(),
                    schedule.getCreatedAt(),
                    schedule.getModifiedAt()
            );
            dtos.add(dto);
        }

        dtos.sort(Comparator.comparing(GetScheduleResponse::getModifiedAt).reversed());
        return dtos;
    }

    @Transactional
    public UpdateScheduleResponse update(Long id, UpdateScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                ()->new IllegalStateException("존재하지 않는 일정 입니다.")
        );

        if (!schedule.getPw().equals(request.getPw())) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        schedule.update(request.getTitle(),request.getAuthorName());

        return new UpdateScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getContents(),
                schedule.getAuthorName(),
                schedule.getCreatedAt(),
                schedule.getModifiedAt());
    }

    @Transactional
    public void delete(Long id, DeleteScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(id).orElseThrow(
                ()->new IllegalStateException("존재하지 않는 일정 입니다.")
        );

        if (!schedule.getPw().equals(request.getPw())) {
            throw new IllegalArgumentException("패스워드가 일치하지 않습니다.");
        }

        scheduleRepository.deleteById(id);
    }
}
