package hello.schedule.service;

import hello.schedule.dto.ScheduleRequest;
import hello.schedule.dto.ScheduleResponse;
import hello.schedule.entity.Schedule;
import hello.schedule.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    private final ScheduleRepository scheduleRepository;

    @Transactional
    public ScheduleResponse save(ScheduleRequest request) {
        Schedule schedule = new Schedule(request.getTitle(), request.getUsername(), request.getContent());
        Schedule savedSchedule = scheduleRepository.save(schedule);
        return new ScheduleResponse(
                savedSchedule.getId(),
                savedSchedule.getTitle(),
                savedSchedule.getUsername(),
                savedSchedule.getContent());
    }

    @Transactional(readOnly = true)
    public List<ScheduleResponse> findAll() {
        List<Schedule> schedules = scheduleRepository.findAll();
        return schedules.stream()
                .map(schedule -> new ScheduleResponse(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getContent(),
                        schedule.getUsername()
                )).toList();
    }

    @Transactional(readOnly = true)
    public ScheduleResponse findById(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("그런 일정의 id가 없어요")
        );
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUsername(),
                schedule.getContent());
    }

    @Transactional
    public ScheduleResponse updateSchedule(Long scheduleId, ScheduleRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("그런 일정의 id는 없어요")
        );
        schedule.setTitle(request.getTitle());
        schedule.setContent(request.getContent());
        schedule.setUsername(request.getUsername());

        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUsername(),
                schedule.getContent());
    }

    @Transactional
    public ScheduleResponse deleteSchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                ()-> new IllegalArgumentException("그런 id의 일정은 없어요")
        );
        scheduleRepository.delete(schedule);
        return new ScheduleResponse(
                schedule.getId(),
                schedule.getTitle(),
                schedule.getUser().getUsername(),  // user 연관관계라면 이렇게
                schedule.getContent()
        );
    }
}
