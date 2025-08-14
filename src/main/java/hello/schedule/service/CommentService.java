package hello.schedule.service;

import hello.schedule.dto.CommentRequest;
import hello.schedule.dto.CommentResponse;
import hello.schedule.entity.Comment;
import hello.schedule.entity.Schedule;
import hello.schedule.entity.User;
import hello.schedule.repository.CommentRepository;
import hello.schedule.repository.ScheduleRepository;
import hello.schedule.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ScheduleRepository scheduleRepository;
    private final UserRepository userRepository;

    @Transactional
    public CommentResponse save(Long scheduleId, CommentRequest request) {
        Schedule schedule = scheduleRepository.findById(scheduleId)
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 일정입니다."));
        User user = userRepository.findById(request.getUserId())
                .orElseThrow(() -> new IllegalArgumentException("존재하지 않는 유저입니다."));

        Comment comment = new Comment(request.getContent(), user, schedule);
        Comment saved = commentRepository.save(comment);

        return new CommentResponse(
                saved.getId(),
                saved.getContent(),
                saved.getUser().getUsername(),
                saved.getSchedule().getId()
        );
    }


    @Transactional(readOnly = true)
    public List<CommentResponse> findAll() {
        List<Comment> comments = commentRepository.findAll();
        return comments.stream()
                .map(comment -> new CommentResponse(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser().getUsername(),
                        comment.getSchedule().getId()
                )).toList();
    }

    @Transactional(readOnly = true)
    public List<CommentResponse> findBySchedule(Long scheduleId) {
        Schedule schedule = scheduleRepository.findById(scheduleId).orElseThrow(
                () -> new IllegalArgumentException("존재하지 않는 일정입니다."));
        return commentRepository.findByScheduleId(scheduleId).stream()
                .map(c -> new CommentResponse(
                        c.getId(),
                        c.getContent(),
                        c.getUser().getUsername(),
                        c.getSchedule().getId()))
                .collect(Collectors.toList());
    }

    @Transactional
    public CommentResponse update(Long commentId, CommentRequest request) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                ()-> new IllegalArgumentException("그런 id의 댓글은 없어요")
        );
        comment.setContent(request.getContent());
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getSchedule().getId()
        );
    }

    @Transactional
    public CommentResponse deleteComment(Long commentId) {
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("그런 id의 댓글은 없어요")
        );
        commentRepository.delete(comment);
        return new CommentResponse(
                comment.getId(),
                comment.getContent(),
                comment.getUser().getUsername(),
                comment.getSchedule().getId()
        );
    }
}
