package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.entity.BigoUser;

public interface BigoUserUpdatesService {

    void processUserStreamUpdate(BigoUser bigoUser, boolean streamingNow, String roomTopic, String streamUrl);

    void checkUsersUpdates();

}
