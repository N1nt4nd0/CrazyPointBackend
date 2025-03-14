package ru.feodorkek.dev.crazypoint.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;
import ru.feodorkek.dev.crazypoint.entity.Avatar;

@Repository
public interface AvatarRepository extends MongoRepository<Avatar, ObjectId> {
}
