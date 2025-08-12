package hello.schedule.controller;

import hello.schedule.dto.ScheduleRequest;
import hello.schedule.dto.ScheduleResponse;
import hello.schedule.service.ScheduleService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class ScheduleController {

    private final ScheduleService scheduleService;

    @PostMapping("/schedules")
    public ResponseEntity<ScheduleResponse> save(
            @RequestBody ScheduleRequest request,
            HttpServletRequest httpRequest
    ) {
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return ResponseEntity.status(401).build();
        }
        Long userId = (Long) session.getAttribute("Login_User");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        request.setUserId(userId);

        return ResponseEntity.ok(scheduleService.save(request));
    }

    @GetMapping("/schedules")
    public ResponseEntity<List<ScheduleResponse>> getAllSchedules() {
        return ResponseEntity.ok(scheduleService.findAll());
    }

    @GetMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> getScheduleById(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(scheduleService.findById(scheduleId));
    }

    @PutMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> updateSchedule(
            @PathVariable Long scheduleId,
            @RequestBody ScheduleRequest request,
            HttpServletRequest httpRequest
    ) {
        HttpSession session = httpRequest.getSession(false);
        if (session == null) {
            return ResponseEntity.status(401).build();
        }

        Long userId = (Long) session.getAttribute("Login_User");
        if (userId == null) {
            return ResponseEntity.status(401).build();
        }
        request.setUserId(userId);

        return ResponseEntity.ok(scheduleService.updateSchedule(scheduleId, request));
    }

    @DeleteMapping("/schedules/{scheduleId}")
    public ResponseEntity<ScheduleResponse> deleteSchedule(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(scheduleService.deleteSchedule(scheduleId));
    }
}
