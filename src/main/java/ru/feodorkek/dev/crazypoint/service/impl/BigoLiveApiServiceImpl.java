package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.feodorkek.dev.crazypoint.config.properties.BigoLiveProperties;
import ru.feodorkek.dev.crazypoint.dto.BigoOfficialUserInfo;
import ru.feodorkek.dev.crazypoint.exception.BigoLiveApiException;
import ru.feodorkek.dev.crazypoint.service.BigoLiveApiService;

@Service
@RequiredArgsConstructor
public class BigoLiveApiServiceImpl implements BigoLiveApiService {

    private final BigoLiveProperties bigoLiveProperties;
    private final RestTemplate restTemplate;

    @Override
    public BigoOfficialUserInfo fetchBigoOfficialUserInfo(final String siteId) {
        final var userInfoResponse = restTemplate.postForObject(bigoLiveProperties.getUserUpdatesApiUrl() + siteId,
                null, BigoOfficialUserInfo.class);
        if (userInfoResponse == null || !userInfoResponse.isSuccess()) {
            throw new BigoLiveApiException("Bigo user info response is null or have error status");
        }
        return userInfoResponse;
    }

}
