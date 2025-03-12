package ru.feodorkek.dev.crazypoint.business;

import ru.feodorkek.dev.crazypoint.dto.BigoStreamDailyChartDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoStreamDaysDtoOut;

import java.time.LocalDate;

public interface BigoStreamChartUseCases {

    BigoStreamDailyChartDtoOut getDailyChartData(String siteId, LocalDate day);

    BigoStreamDaysDtoOut getStreamDaysData(String siteId);

}
