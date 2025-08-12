package hello.schedule.dto;

import lombok.Getter;

@Getter
public class ScheduleRequest {

    private String title;
    private String content;
    private Long userId;

    public void setUserId(Long userId) {
        this.userId = userId;
    }
}
