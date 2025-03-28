package ru.feodorkek.dev.crazypoint.mapper.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.config.properties.BigoLiveProperties;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;
import ru.feodorkek.dev.crazypoint.mapper.BigoUserMapper;

@Component
@RequiredArgsConstructor
public class BigoUserMapperImpl implements BigoUserMapper {

    private final BigoLiveProperties bigoLiveProperties;

    @Override
    public BigoUserDtoOut toBigoUserDtoOut(final BigoUser bigoUser) {
        return new BigoUserDtoOut(
                bigoUser.getSiteId(),
                bigoLiveProperties.formatUserLink(bigoUser.getSiteId()),
                bigoUser.getDisplayName(),
                bigoUser.getTimeZone(),
                bigoUser.getStartStreamMessage(),
                bigoUser.getEndStreamMessage(),
                bigoUser.isStreamingNow(),
                bigoUser.isShowStreamMessages());
    }

}
