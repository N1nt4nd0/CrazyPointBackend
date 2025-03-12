package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.dto.BigoOfficialUserInfo;

public interface BigoLiveApiService {

    BigoOfficialUserInfo fetchBigoOfficialUserInfo(String siteId);

}
