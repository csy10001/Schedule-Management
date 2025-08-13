package hello.schedule.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
public class ScheduleRequest {

    private String title;
    private String content;
    @Setter
    private Long userId;

}
