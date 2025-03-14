package ru.feodorkek.dev.crazypoint.business;

import ru.feodorkek.dev.crazypoint.dto.BigoStreamChartDailyDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoStreamDaysDtoOut;

import java.time.LocalDate;

public interface BigoStreamChartUseCases {

    BigoStreamChartDailyDtoOut getChartDailyData(String siteId, LocalDate day);

    BigoStreamDaysDtoOut getStreamDaysData(String siteId);

}
