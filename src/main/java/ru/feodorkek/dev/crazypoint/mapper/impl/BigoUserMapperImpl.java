package ru.feodorkek.dev.crazypoint.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.config.properties.BigoLiveProperties;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;
import ru.feodorkek.dev.crazypoint.event.BigoUserEndStreamEvent;
import ru.feodorkek.dev.crazypoint.event.BigoUserStartStreamEvent;
import ru.feodorkek.dev.crazypoint.mapper.BigoUserMapper;

@Component
@RequiredArgsConstructor
public class BigoUserMapperImpl implements BigoUserMapper {

    private final BigoLiveProperties bigoLiveProperties;

    private String formatUserLink(final BigoUser bigoUser) {
        return bigoLiveProperties.formatUserLink(bigoUser.getSiteId());
    }

    @Override
    public BigoUserDtoOut toBigoUserDtoOut(final BigoUser bigoUser) {
        return new BigoUserDtoOut(
                bigoUser.getSiteId(),
                formatUserLink(bigoUser),
                bigoUser.getDisplayName(),
                bigoUser.getTimeZone(),
                bigoUser.getStartStreamMessage(),
                bigoUser.getEndStreamMessage(),
                bigoUser.isStreamingNow(),
                bigoUser.isShowStreamMessages());
    }

    @Override
    public BigoUserStartStreamEvent toBigoUserStartStreamEvent(final BigoUser bigoUser) {
        return new BigoUserStartStreamEvent(
                bigoUser.getSiteId(),
                formatUserLink(bigoUser),
                bigoUser.getDisplayName(),
                bigoUser.getTimeZone(),
                bigoUser.getStartStreamMessage(),
                bigoUser.getLastStreamSession().getRoomTopic(),
                bigoUser.getLastStreamSession().getStartTime(),
                bigoUser.isShowStreamMessages());
    }

    @Override
    public BigoUserEndStreamEvent toBigoUserEndStreamEvent(final BigoUser bigoUser) {
        return new BigoUserEndStreamEvent(
                bigoUser.getSiteId(),
                formatUserLink(bigoUser),
                bigoUser.getDisplayName(),
                bigoUser.getTimeZone(),
                bigoUser.getEndStreamMessage(),
                bigoUser.getLastStreamSession().getRoomTopic(),
                bigoUser.getLastStreamSession().getStartTime(),
                bigoUser.getLastStreamSession().getEndTime(),
                bigoUser.isShowStreamMessages());
    }

}
