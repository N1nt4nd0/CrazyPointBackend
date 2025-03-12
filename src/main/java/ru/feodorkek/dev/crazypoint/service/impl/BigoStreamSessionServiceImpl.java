package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.feodorkek.dev.crazypoint.entity.BigoStreamSession;
import ru.feodorkek.dev.crazypoint.repository.BigoStreamSessionRepository;
import ru.feodorkek.dev.crazypoint.service.BigoStreamSessionService;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Service
@RequiredArgsConstructor
public class BigoStreamSessionServiceImpl implements BigoStreamSessionService {

    private final BigoStreamSessionRepository repository;

    @Override
    @Transactional
    public List<BigoStreamSession> getEndedSessionsForDayQueryMethod(final String siteId,
                                                                     final LocalDate day,
                                                                     final ZoneId timeZone) {
        final var from = day.atStartOfDay().atZone(timeZone).toInstant();
        final var to = day.atTime(23, 59, 59).atZone(timeZone).toInstant();
        return repository.findBySiteIdAndBetweenInstants(siteId, from, to);
    }

    @Override
    @Transactional
    public List<BigoStreamSession> getEndedSessionsForDayServiceMethod(final String siteId,
                                                                       final LocalDate day,
                                                                       final ZoneId timeZone) {
        return getAllEndedSessions(siteId).stream()
                .filter(session -> {
                    final var startDay = session.getStartTime().atZone(timeZone).toLocalDate();
                    final var endDay = session.getEndTime().atZone(timeZone).toLocalDate();
                    return startDay.equals(day) || endDay.equals(day) ||
                            (startDay.isBefore(day) && endDay.isAfter(day));
                })
                .toList();
    }

    @Override
    @Transactional
    public List<LocalDate> getEndedSessionsDaysList(final String siteId, final ZoneId timeZone) {
        return getAllEndedSessions(siteId).stream()
                .flatMap(session -> {
                    final var startDay = session.getStartTime().atZone(timeZone).toLocalDate();
                    final var endDay = session.getEndTime().atZone(timeZone).toLocalDate();
                    return Stream.iterate(startDay, date -> date.plusDays(1))
                            .limit(ChronoUnit.DAYS.between(startDay, endDay) + 1);
                })
                .distinct()
                .sorted(Comparator.reverseOrder())
                .toList();
    }

    @Override
    @Transactional
    public List<BigoStreamSession> getAllEndedSessions(final String siteId) {
        return repository.findBySiteIdAndEndTimeNotNull(siteId);
    }

    @Override
    @Transactional
    public BigoStreamSession createNewSession(final String siteId,
                                              final String roomTopic,
                                              final String timeZone,
                                              final Instant startTime) {
        return saveSession(new BigoStreamSession(null, siteId, roomTopic, timeZone, startTime, null));
    }

    @Override
    @Transactional
    public BigoStreamSession saveSession(final BigoStreamSession session) {
        return repository.save(session);
    }

}
