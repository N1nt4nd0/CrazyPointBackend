package ru.feodorkek.dev.crazypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BigoStreamSessionChartData {

    private final String sessionId;
    private final String start;
    private final String end;
    private final String title;
    private final String roomTopic;

    @JsonCreator
    public BigoStreamSessionChartData(
            @JsonProperty("sessionId") final String sessionId,
            @JsonProperty("start") final String start,
            @JsonProperty("end") final String end,
            @JsonProperty("title") final String title,
            @JsonProperty("roomTopic") final String roomTopic) {
        this.roomTopic = roomTopic;
        this.sessionId = sessionId;
        this.title = title;
        this.start = start;
        this.end = end;
    }

}
