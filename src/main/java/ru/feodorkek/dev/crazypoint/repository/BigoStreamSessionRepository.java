package ru.feodorkek.dev.crazypoint.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;
import ru.feodorkek.dev.crazypoint.entity.BigoStreamSession;

import java.time.Instant;
import java.util.List;

@Repository
public interface BigoStreamSessionRepository extends MongoRepository<BigoStreamSession, ObjectId> {

    @Query("""
            {'siteId': ?0, 'endTime': {$ne: null}, $or: [
            {'startTime': {$gte: ?1, $lte: ?2}},
            {'endTime': {$gte: ?1, $lte: ?2}},
            {'startTime': {$lte: ?1}, 'endTime': {$gte: ?2}}]}
            """)
    List<BigoStreamSession> findBySiteIdAndBetweenInstants(String siteId, Instant from, Instant to);

    List<BigoStreamSession> findBySiteIdAndEndTimeNotNull(String siteId);

}
