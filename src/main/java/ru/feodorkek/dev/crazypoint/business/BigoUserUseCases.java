package ru.feodorkek.dev.crazypoint.business;

import ru.feodorkek.dev.crazypoint.dto.BigoOfficialUserInfo;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoIn;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoUsersListDtoOut;

public interface BigoUserUseCases {

    BigoUserDtoOut createNewUser(BigoUserDtoIn bigoUserDtoIn);

    BigoUserDtoOut updateUser(BigoUserDtoIn bigoUserDtoIn);

    void deleteUserBySiteId(String siteId);

    BigoUserDtoOut updateShowStreamMessages(String siteId, boolean showStreamMessages);

    BigoUsersListDtoOut getAllUsers();

    void startBigoStream(String siteId, String roomTopic);

    void endBigoStream(String siteId);

    BigoOfficialUserInfo fetchBigoOfficialUserInfo(String siteId);

}
