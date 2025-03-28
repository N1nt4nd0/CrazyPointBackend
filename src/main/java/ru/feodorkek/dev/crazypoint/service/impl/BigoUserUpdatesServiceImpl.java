package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.config.properties.BigoLiveProperties;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;
import ru.feodorkek.dev.crazypoint.service.BigoLiveApiService;
import ru.feodorkek.dev.crazypoint.service.BigoUserService;
import ru.feodorkek.dev.crazypoint.service.BigoUserStreamService;
import ru.feodorkek.dev.crazypoint.service.BigoUserUpdatesService;
import ru.feodorkek.dev.crazypoint.service.DateTimeService;

@Slf4j
@Component
@RequiredArgsConstructor
public class BigoUserUpdatesServiceImpl implements BigoUserUpdatesService {

    private final BigoUserStreamService bigoUserStreamService;
    private final BigoLiveProperties bigoLiveProperties;
    private final BigoLiveApiService bigoLiveApiService;
    private final DateTimeService dateTimeService;
    private final BigoUserService bigoUserService;

    @Override
    public void processUserStreamUpdate(final BigoUser bigoUser,
                                        final boolean streamingNow,
                                        final String roomTopic,
                                        final String streamUrl) {
        if (streamingNow) {
            if (!bigoUser.isStreamingNow()) {
                bigoUserStreamService.startStream(bigoUser, roomTopic, streamUrl,
                        dateTimeService.instantNowWithTruncatedSeconds());
            }
        } else {
            if (bigoUser.isStreamingNow()) {
                bigoUserStreamService.endStream(bigoUser,
                        dateTimeService.instantNowWithTruncatedSeconds());
            }
        }
    }

    @Override
    @Scheduled(fixedDelayString = "${crazypoint.bigo-live.user-updates-check-delay-ms}")
    public void checkUsersUpdates() {
        if (!bigoLiveProperties.isUserUpdatesCheckEnabled()) {
            return;
        }
        for (final var bigoUser : bigoUserService.getAllUsers()) {
            try {
                final var data = bigoLiveApiService.fetchBigoOfficialUserInfo(bigoUser.getSiteId()).getData();
                try {
                    processUserStreamUpdate(bigoUser, data.isStreamingNow(), data.getRoomTopic(), data.getHls_src());
                } catch (final Exception exception) {
                    log.error("Can't process stream update for BigoUser with siteId: {}",
                            bigoUser.getSiteId(), exception);
                }
            } catch (final Exception exception) {
                log.error("Can't fetch official user info for BigoUser with siteId: {}",
                        bigoUser.getSiteId(), exception);
            }
        }
    }

}
