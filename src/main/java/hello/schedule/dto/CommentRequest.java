package hello.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class CommentRequest {
    private String content;
    private Long scheduleId;
    @Setter
    private Long userId;
}
