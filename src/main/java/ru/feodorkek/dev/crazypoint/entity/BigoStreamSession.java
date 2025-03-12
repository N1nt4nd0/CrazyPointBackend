package ru.feodorkek.dev.crazypoint.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.Instant;

@Document("bigo_live_stream_sessions")
@Getter
@ToString
@RequiredArgsConstructor
public class BigoStreamSession {

    public static final int VERSION = 3;

    @Id
    private final ObjectId id;
    private final int version = VERSION;

    private final String siteId;
    private final String roomTopic;
    private final String timeZone;
    private final Instant startTime;
    private final Instant endTime;

    public BigoStreamSession withEndTime(final Instant endTime) {
        return new BigoStreamSession(id, siteId, roomTopic, timeZone, startTime, endTime);
    }

    public boolean isActive() {
        return startTime != null && endTime == null;
    }

}
