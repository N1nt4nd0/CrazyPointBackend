package ru.feodorkek.dev.crazypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

@Getter
@ToString
public class BigoStreamChartDailyDtoOut {

    private final String bigoUserName;
    private final List<BigoStreamSessionChartData> chartData;
    private final String totalDailyTime;

    @JsonCreator
    public BigoStreamChartDailyDtoOut(
            @JsonProperty("bigoUserName") final String bigoUserName,
            @JsonProperty("chartData") final List<BigoStreamSessionChartData> chartData,
            @JsonProperty("totalDailyTime") final String totalDailyTime) {
        this.totalDailyTime = totalDailyTime;
        this.bigoUserName = bigoUserName;
        this.chartData = chartData;
    }
}
