package ru.feodorkek.dev.crazypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;

@Getter
@ToString
public class BigoStreamPartChartData implements Serializable {

    private final String start;
    private final String end;
    private final String title;
    private final String subTitle;

    @JsonCreator
    public BigoStreamPartChartData(@JsonProperty("start") final String start,
                                   @JsonProperty("end") final String end,
                                   @JsonProperty("title") final String title,
                                   @JsonProperty("subTitle") final String subTitle) {
        this.start = start;
        this.end = end;
        this.title = title;
        this.subTitle = subTitle;
    }

}
