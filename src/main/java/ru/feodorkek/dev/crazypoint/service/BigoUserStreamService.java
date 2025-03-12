package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.entity.BigoUser;

import java.time.Instant;

public interface BigoUserStreamService {

    void startStream(BigoUser bigoUser, String roomTopic, Instant startTime);

    void endStream(BigoUser bigoUser, Instant endTime);

}
