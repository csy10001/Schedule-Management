package hello.schedule.controller;

import hello.schedule.dto.CommentRequest;
import hello.schedule.dto.CommentResponse;
import hello.schedule.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CommentController {

    private final CommentService commentService;

    @PostMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<CommentResponse> save(
            @RequestBody CommentRequest request,
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(commentService.save(scheduleId, request));
    }


    @GetMapping("/schedules/{scheduleId}/comments")
    public ResponseEntity<List<CommentResponse>> getAllComments(
            @PathVariable Long scheduleId
    ) {
        return ResponseEntity.ok(commentService.findAll());
    }

    @GetMapping("schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<List<CommentResponse>> getCommentById(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId
    ) {
        return ResponseEntity.ok(commentService.findBySchedule(scheduleId));
    }

    @PutMapping("schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> updateComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId,
            @RequestBody CommentRequest request
    ) {
        return ResponseEntity.ok(commentService.update(scheduleId, request));
    }

    @DeleteMapping("schedules/{scheduleId}/comments/{commentId}")
    public ResponseEntity<CommentResponse> deleteComment(
            @PathVariable Long scheduleId,
            @PathVariable Long commentId
    ) {
        return ResponseEntity.ok(commentService.deleteComment(commentId));
    }
}
