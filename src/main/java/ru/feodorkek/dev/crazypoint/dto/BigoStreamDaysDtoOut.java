package ru.feodorkek.dev.crazypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class BigoStreamDaysDtoOut {

    private final String bigoUserName;
    private final List<String> streamDays;

    @JsonCreator
    public BigoStreamDaysDtoOut(@JsonProperty("bigoUserName") final String bigoUserName,
                                @JsonProperty("streamDays") final List<String> streamDays) {
        this.bigoUserName = bigoUserName;
        this.streamDays = streamDays;
    }

}
