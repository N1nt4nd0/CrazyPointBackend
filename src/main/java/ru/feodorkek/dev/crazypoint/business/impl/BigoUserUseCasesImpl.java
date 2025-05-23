package ru.feodorkek.dev.crazypoint.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.business.BigoUserUseCases;
import ru.feodorkek.dev.crazypoint.config.data.CacheKeyBuilder;
import ru.feodorkek.dev.crazypoint.dto.BigoOfficialUserInfo;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoIn;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoUsersListDtoOut;
import ru.feodorkek.dev.crazypoint.mapper.BigoUserMapper;
import ru.feodorkek.dev.crazypoint.service.BigoLiveApiService;
import ru.feodorkek.dev.crazypoint.service.BigoUserService;
import ru.feodorkek.dev.crazypoint.service.BigoUserStreamService;
import ru.feodorkek.dev.crazypoint.service.DateTimeService;

@Service
@RequiredArgsConstructor
public class BigoUserUseCasesImpl implements BigoUserUseCases {

    private final BigoUserStreamService bigoUserStreamService;
    private final BigoLiveApiService bigoLiveApiService;
    private final DateTimeService dateTimeService;
    private final BigoUserService bigoUserService;
    private final BigoUserMapper bigoUserMapper;

    @Override
    public BigoUserDtoOut createNewUser(final BigoUserDtoIn bigoUserDtoIn) {
        final var newUser = bigoUserService.createNewUser(
                bigoUserDtoIn.getSiteId(),
                bigoUserDtoIn.getDisplayName(),
                bigoUserDtoIn.getTimeZone(),
                bigoUserDtoIn.getStartStreamMessage(),
                bigoUserDtoIn.getEndStreamMessage(),
                bigoUserDtoIn.isShowStreamMessages());
        return bigoUserMapper.toBigoUserDtoOut(newUser);
    }

    @Override
    public BigoUserDtoOut updateUser(final BigoUserDtoIn bigoUserDtoIn) {
        final var updatedUser = bigoUserService.updateUser(
                bigoUserDtoIn.getSiteId(),
                bigoUserDtoIn.getDisplayName(),
                bigoUserDtoIn.getTimeZone(),
                bigoUserDtoIn.getStartStreamMessage(),
                bigoUserDtoIn.getEndStreamMessage(),
                bigoUserDtoIn.isShowStreamMessages());
        return bigoUserMapper.toBigoUserDtoOut(updatedUser);
    }

    @Override
    public void deleteUserBySiteId(final String siteId) {
        bigoUserService.deleteUserBySiteId(siteId);
    }

    @Override
    public BigoUserDtoOut updateShowStreamMessages(final String siteId, final boolean showStreamMessages) {
        final var updatedUser = bigoUserService.updateShowStreamMessages(siteId, showStreamMessages);
        return bigoUserMapper.toBigoUserDtoOut(updatedUser);
    }

    @Override
    @Cacheable(
            value = CacheKeyBuilder.BIGO_USERS_LIST_CACHE_NAME,
            key = "@cacheKeyBuilder.buildBigoUsersListCacheKey()"
    )
    public BigoUsersListDtoOut getAllUsers() {
        final var bigoUsers = bigoUserService.getAllUsers()
                .stream()
                .map(bigoUserMapper::toBigoUserDtoOut)
                .toList();
        return new BigoUsersListDtoOut(bigoUsers, bigoUsers.size());
    }

    @Override
    public void startBigoStream(final String siteId, final String roomTopic, final String streamUrl) {
        final var bigoUser = bigoUserService.getUserBySiteId(siteId);
        bigoUserStreamService.startStream(bigoUser, roomTopic, streamUrl,
                dateTimeService.instantNowWithTruncatedSeconds());
    }

    @Override
    public void endBigoStream(final String siteId) {
        final var bigoUser = bigoUserService.getUserBySiteId(siteId);
        bigoUserStreamService.endStream(bigoUser, dateTimeService.instantNowWithTruncatedSeconds());
    }

    @Override
    public BigoOfficialUserInfo fetchBigoOfficialUserInfo(final String siteId) {
        return bigoLiveApiService.fetchBigoOfficialUserInfo(siteId);
    }

}
