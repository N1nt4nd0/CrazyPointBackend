package ru.feodorkek.dev.crazypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Getter
@ToString
public class BigoStreamDailyChartDtoOut implements Serializable {

    private final String bigoUserName;
    private final List<BigoStreamPartChartData> chartData;
    private final String totalDailyTime;

    @JsonCreator
    public BigoStreamDailyChartDtoOut(@JsonProperty("bigoUserName") final String bigoUserName,
                                      @JsonProperty("chartData") final List<BigoStreamPartChartData> chartData,
                                      @JsonProperty("totalDailyTime") final String totalDailyTime) {
        this.bigoUserName = bigoUserName;
        this.chartData = chartData;
        this.totalDailyTime = totalDailyTime;
    }
}
