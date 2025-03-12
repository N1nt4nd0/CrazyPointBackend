package ru.feodorkek.dev.crazypoint.business;

import ru.feodorkek.dev.crazypoint.dto.BigoOfficialUserInfo;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoIn;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoUsersListDtoOut;
import ru.feodorkek.dev.crazypoint.dto.DeleteBigoUserDtoOut;

public interface BigoUserUseCases {

    BigoUserDtoOut createNewUser(BigoUserDtoIn bigoUserDtoIn);

    BigoUserDtoOut updateUser(BigoUserDtoIn bigoUserDtoIn);

    DeleteBigoUserDtoOut deleteUserBySiteId(String siteId);

    BigoUserDtoOut updateShowStreamMessages(String siteId, boolean showStreamMessages);

    BigoUsersListDtoOut getAllUsers();

    void startBigoStream(String siteId, String roomTopic);

    void endBigoStream(String siteId);

    BigoOfficialUserInfo fetchBigoOfficialUserInfo(String siteId);

}
