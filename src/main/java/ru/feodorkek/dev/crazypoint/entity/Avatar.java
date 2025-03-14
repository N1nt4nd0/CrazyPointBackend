package ru.feodorkek.dev.crazypoint.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;
import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("avatars")
@Getter
@ToString
@RequiredArgsConstructor
public class Avatar {

    public static final int VERSION = 1;

    @Id
    private final ObjectId id;
    private final int version = VERSION;

    private final String avatarUrl;

}
