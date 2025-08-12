package hello.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleResponse {

    private final Long id;
    private final String title;
    private final String username;
    private final String content;

    public ScheduleResponse(Long id, String title, String username, String content) {
        this.id = id;
        this.title = title;
        this.username = username;
        this.content = content;
    }
}
