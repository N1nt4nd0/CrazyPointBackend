package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.entity.BigoStreamSession;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.List;

public interface BigoStreamSessionService {

    List<BigoStreamSession> getEndedSessionsForDayQueryMethod(String siteId,
                                                              LocalDate day,
                                                              ZoneId timeZone);

    List<BigoStreamSession> getEndedSessionsForDayServiceMethod(String siteId,
                                                                LocalDate day,
                                                                ZoneId timeZone);

    List<LocalDate> getEndedSessionsDaysList(String siteId, ZoneId timeZone);

    List<BigoStreamSession> getAllEndedSessions(String siteId);

    BigoStreamSession createNewSession(String siteId, String roomTopic, String timeZone, Instant startTime);

    BigoStreamSession saveSession(BigoStreamSession session);

}
