package ru.feodorkek.dev.crazypoint.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;

import java.util.Optional;

@Repository
public interface BigoUserRepository extends MongoRepository<BigoUser, ObjectId> {

    Optional<BigoUser> findBySiteId(String siteId);

}
