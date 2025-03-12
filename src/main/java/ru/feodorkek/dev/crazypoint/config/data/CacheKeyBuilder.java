package ru.feodorkek.dev.crazypoint.config.data;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;
import ru.feodorkek.dev.crazypoint.service.DateTimeService;

import java.time.LocalDate;
import java.time.ZoneId;

@Component
@RequiredArgsConstructor
public class CacheKeyBuilder {

    public static final String BIGO_STREAM_DAILY_CHART_CACHE_NAME = "bigo_stream_daily_chart_cache";
    public static final String BIGO_STREAM_DAYS_CACHE_NAME = "bigo_stream_days_cache";

    private final DateTimeService dateTimeService;

    public String buildBigoStreamDailyChartCacheKey(final String siteId, final LocalDate day) {
        return String.format("%s_%s", siteId, day.format(dateTimeService.daysMonthYearFormatter()));
    }

    public String buildBigoStreamDailyChartCacheKey(final BigoUser bigoUser) {
        final var streamSession = bigoUser.getLastStreamSession();
        final var timeZone = ZoneId.of(streamSession.getTimeZone());
        final var startDay = streamSession.getStartTime().atZone(timeZone).toLocalDate();
        return buildBigoStreamDailyChartCacheKey(bigoUser.getSiteId(), startDay);
    }


}
