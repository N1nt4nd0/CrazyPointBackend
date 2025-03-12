package ru.feodorkek.dev.crazypoint.event;

import lombok.Data;

import java.time.Instant;

@Data
public class BigoUserEndStreamEvent {

    private final String siteId;
    private final String userLink;
    private final String displayName;
    private final String userTimeZone;
    private final String endStreamMessage;
    private final String roomTopic;
    private final Instant startStreamTime;
    private final Instant endStreamTime;
    private final boolean showStreamMessage;

}
