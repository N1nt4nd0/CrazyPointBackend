package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.config.data.CacheKeyBuilder;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;
import ru.feodorkek.dev.crazypoint.exception.BigoUserStreamException;
import ru.feodorkek.dev.crazypoint.mapper.BigoUserMapper;
import ru.feodorkek.dev.crazypoint.service.BigoStreamSessionService;
import ru.feodorkek.dev.crazypoint.service.BigoUserEventHandlerService;
import ru.feodorkek.dev.crazypoint.service.BigoUserService;
import ru.feodorkek.dev.crazypoint.service.BigoUserStreamService;

import java.time.Instant;

@Service
@RequiredArgsConstructor
public class BigoUserStreamServiceImpl implements BigoUserStreamService {

    private final BigoUserEventHandlerService bigoUserEventHandlerService;
    private final BigoStreamSessionService sessionService;
    private final BigoUserMapper bigoUserMapper;
    private final BigoUserService userService;

    @Override
    public void startStream(final BigoUser bigoUser, final String roomTopic, final Instant startTime) {
        if (bigoUser.isStreamingNow()) {
            throw new BigoUserStreamException("Can't process start stream. Already streaming");
        }
        final var streamSession = sessionService.createNewSession(bigoUser.getSiteId(), roomTopic,
                bigoUser.getTimeZone(), startTime);
        final var updatedUser = userService.saveUser(bigoUser.withLastStreamSession(streamSession));
        bigoUserEventHandlerService.postStartStreamEvent(bigoUserMapper
                .toBigoUserStartStreamEvent(updatedUser));
    }

    @Override
    @Caching(evict = {
            @CacheEvict(
                    value = CacheKeyBuilder.BIGO_STREAM_DAYS_CACHE_NAME,
                    key = "#bigoUser.siteId"
            ),
            @CacheEvict(
                    value = CacheKeyBuilder.BIGO_STREAM_DAILY_CHART_CACHE_NAME,
                    key = "@cacheKeyBuilder.buildBigoStreamChartDailyCacheKey(#bigoUser)"
            )}
    )
    public void endStream(final BigoUser bigoUser, final Instant endTime) {
        if (!bigoUser.isStreamingNow()) {
            throw new BigoUserStreamException("Can't process end stream. Not streaming now");
        }
        final var streamSession = bigoUser.getLastStreamSession().withEndTime(endTime);
        final var updatedStreamSession = sessionService.saveSession(streamSession);
        final var updatedUser = userService.saveUser(bigoUser.withLastStreamSession(updatedStreamSession));
        bigoUserEventHandlerService.postEndStreamEvent(bigoUserMapper
                .toBigoUserEndStreamEvent(updatedUser));
    }

}
