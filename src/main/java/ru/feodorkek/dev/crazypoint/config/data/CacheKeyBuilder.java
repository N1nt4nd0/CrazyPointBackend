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

    public static final String BIGO_USER_STREAM_CHART_DAILY_CACHE_NAME = "bigo_user_stream_chart_daily_cache";
    public static final String BIGO_USER_STREAM_DAYS_CACHE_NAME = "bigo_user_stream_days_cache";
    public static final String BIGO_USERS_LIST_CACHE_NAME = "bigo_users_list_cache";

    private final DateTimeService dateTimeService;

    public String buildBigoUserStreamChartDailyCacheKey(final String siteId, final LocalDate day) {
        return String.format("%s_%s", siteId, day.format(dateTimeService.daysMonthYearFormatter()));
    }

    public String buildBigoUserStreamChartDailyCacheKey(final BigoUser bigoUser) {
        final var streamSession = bigoUser.getLastStreamSession();
        final var timeZone = ZoneId.of(streamSession.getTimeZone());
        final var startDay = streamSession.getStartTime().atZone(timeZone).toLocalDate();
        return buildBigoUserStreamChartDailyCacheKey(bigoUser.getSiteId(), startDay);
    }


}
