package hello.schedule.dto;

import lombok.Getter;

@Getter
public class CommentResponse {
    private final Long id;
    private final String content;
    private final String username;
    private final Long scheduleId;

    public CommentResponse(Long id, String content, String username, Long scheduleId) {
        this.id = id;
        this.content = content;
        this.username = username;
        this.scheduleId = scheduleId;
    }
}
