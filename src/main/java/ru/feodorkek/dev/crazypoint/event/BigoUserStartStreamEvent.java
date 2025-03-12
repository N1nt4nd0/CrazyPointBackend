package ru.feodorkek.dev.crazypoint.event;

import lombok.Data;
import ru.feodorkek.dev.crazypoint.util.StringUnit;

import java.time.Instant;

@Data
public class BigoUserStartStreamEvent {

    private final String siteId;
    private final String userLink;
    private final String displayName;
    private final String userTimeZone;
    private final String startStreamMessage;
    private final String roomTopic;
    private final Instant startStreamTime;
    private final boolean showStreamMessage;

    public boolean hasRoomTopic() {
        return StringUnit.isNotBlank(roomTopic);
    }

}
