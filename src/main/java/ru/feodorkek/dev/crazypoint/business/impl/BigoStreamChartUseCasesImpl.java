package ru.feodorkek.dev.crazypoint.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.business.BigoStreamChartUseCases;
import ru.feodorkek.dev.crazypoint.config.data.CacheKeyBuilder;
import ru.feodorkek.dev.crazypoint.dto.BigoStreamChartDailyDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoStreamDaysDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoStreamPartChartData;
import ru.feodorkek.dev.crazypoint.exception.BigoStreamChartException;
import ru.feodorkek.dev.crazypoint.service.BigoStreamSessionService;
import ru.feodorkek.dev.crazypoint.service.BigoUserService;
import ru.feodorkek.dev.crazypoint.service.DateTimeService;

import java.time.Duration;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BigoStreamChartUseCasesImpl implements BigoStreamChartUseCases {

    private final BigoStreamSessionService streamSessionService;
    private final DateTimeService dateTimeService;
    private final BigoUserService bigoUserService;

    private String calculateStartTime(final ZonedDateTime startTime,
                                      final LocalDate day,
                                      final DateTimeFormatter formatter) {
        return startTime.toLocalDate().isBefore(day) ? "00:00" : startTime.format(formatter);
    }

    private String calculateEndTime(final ZonedDateTime endTime,
                                    final LocalDate day,
                                    final DateTimeFormatter formatter) {
        return endTime.toLocalDate().isAfter(day) ? "23:59" : endTime.format(formatter);
    }

    private long calculateDuration(final ZonedDateTime startTime,
                                   final ZonedDateTime endTime,
                                   final LocalDate day,
                                   final ZoneId timeZone) {
        final var from = startTime.toLocalDate().isBefore(day) ?
                day.atStartOfDay(timeZone).toInstant() : startTime.toInstant();
        final var to = endTime.toLocalDate().isAfter(day) ?
                day.plusDays(1).atStartOfDay(timeZone).toInstant() : endTime.toInstant();
        return Duration.between(from, to).toSeconds();
    }

    private void addChartData(final List<BigoStreamPartChartData> chartData,
                              final String startLabel,
                              final String endLabel,
                              final long secondsPart,
                              final String topic) {
        chartData.add(new BigoStreamPartChartData(startLabel, endLabel,
                formatTooltipTitle(startLabel, endLabel, secondsPart), topic));
    }

    private String formatTooltipTitle(final String startLabel, final String endLabel, final long secondsPart) {
        return String.format("[%s-%s] (%s)", startLabel, endLabel,
                dateTimeService.secondsToHoursMinutesSecondsFormat(secondsPart));
    }

    @Override
    @Cacheable(
            value = CacheKeyBuilder.BIGO_USER_STREAM_CHART_DAILY_CACHE_NAME,
            key = "@cacheKeyBuilder.buildBigoUserStreamChartDailyCacheKey(#siteId, #day)"
    )
    public BigoStreamChartDailyDtoOut getChartDailyData(final String siteId,
                                                        final LocalDate day) {
        final var bigoUser = bigoUserService.getUserBySiteId(siteId);
        final var timeZone = ZoneId.of(bigoUser.getTimeZone());
        final var sessions = streamSessionService.getEndedSessionsForDayQueryMethod(siteId, day, timeZone);
        if (sessions.isEmpty()) {
            throw new BigoStreamChartException("Can't find stream sessions for: " + day);
        }
        final var timeFormatter = dateTimeService.hoursMinutesFormatter();
        final var chartData = new ArrayList<BigoStreamPartChartData>();
        var totalSeconds = 0L;
        for (final var session : sessions) {
            final var startTime = session.getStartTime().atZone(timeZone);
            final var endTime = session.getEndTime().atZone(timeZone);
            final var startLabel = calculateStartTime(startTime, day, timeFormatter);
            final var endLabel = calculateEndTime(endTime, day, timeFormatter);
            final var secondsPart = calculateDuration(startTime, endTime, day, timeZone);
            addChartData(chartData, startLabel, endLabel, secondsPart, session.getRoomTopic());
            totalSeconds += secondsPart;
        }
        return new BigoStreamChartDailyDtoOut(bigoUser.getDisplayName(), chartData,
                dateTimeService.secondsToHoursMinutesSecondsFormat(totalSeconds));
    }

    @Override
    @Cacheable(
            value = CacheKeyBuilder.BIGO_USER_STREAM_DAYS_CACHE_NAME,
            key = "#siteId"
    )
    public BigoStreamDaysDtoOut getStreamDaysData(final String siteId) {
        final var bigoUser = bigoUserService.getUserBySiteId(siteId);
        final var timeZone = ZoneId.of(bigoUser.getTimeZone());
        final var streamDays = streamSessionService.getEndedSessionsDaysList(siteId, timeZone).stream()
                .map(date -> date.format(dateTimeService.daysMonthYearFormatter())).toList();
        return new BigoStreamDaysDtoOut(bigoUser.getDisplayName(), streamDays);
    }

}
