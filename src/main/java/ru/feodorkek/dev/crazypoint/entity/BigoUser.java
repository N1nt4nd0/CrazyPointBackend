package ru.feodorkek.dev.crazypoint.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Objects;

@Document("bigo_live_user_updates")
@Getter
@ToString
@RequiredArgsConstructor
public class BigoUser {

    public static final int VERSION = 3;

    @Id
    private final ObjectId id;
    private final int version = VERSION;

    @Indexed(name = "site_id_index", unique = true, collation = "{locale: 'en', strength: 2}")
    private final String siteId;
    private final String displayName;
    private final String timeZone;
    private final String startStreamMessage;
    private final String endStreamMessage;
    private final boolean showStreamMessages;
    @ToString.Exclude
    @DBRef(lazy = true)
    private final BigoStreamSession lastStreamSession;

    public BigoUser withDisplayName(final String displayName) {
        return new BigoUser(id, siteId, displayName, timeZone,
                startStreamMessage, endStreamMessage, showStreamMessages, lastStreamSession);
    }

    public BigoUser withTimeZone(final String timeZone) {
        return new BigoUser(id, siteId, displayName, timeZone,
                startStreamMessage, endStreamMessage, showStreamMessages, lastStreamSession);
    }

    public BigoUser withStartStreamMessage(final String startStreamMessage) {
        return new BigoUser(id, siteId, displayName, timeZone,
                startStreamMessage, endStreamMessage, showStreamMessages, lastStreamSession);
    }

    public BigoUser withEndStreamMessage(final String endStreamMessage) {
        return new BigoUser(id, siteId, displayName, timeZone,
                startStreamMessage, endStreamMessage, showStreamMessages, lastStreamSession);
    }

    public BigoUser withShowStreamMessages(final boolean showStreamMessages) {
        return new BigoUser(id, siteId, displayName, timeZone,
                startStreamMessage, endStreamMessage, showStreamMessages, lastStreamSession);
    }

    public BigoUser withLastStreamSession(final BigoStreamSession lastStreamSession) {
        return new BigoUser(id, siteId, displayName, timeZone,
                startStreamMessage, endStreamMessage, showStreamMessages, lastStreamSession);
    }

    public boolean isStreamingNow() {
        return lastStreamSession != null && lastStreamSession.isActive();
    }

    @Override
    public boolean equals(final Object object) {
        if (object instanceof BigoUser user) {
            return Objects.equals(siteId, user.siteId);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(siteId);
    }

}