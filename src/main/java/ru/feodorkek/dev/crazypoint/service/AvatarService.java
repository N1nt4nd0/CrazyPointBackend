package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.entity.Avatar;

import java.util.List;

public interface AvatarService {

    Avatar createNewAvatar(String avatarUrl);

    Avatar saveAvatar(Avatar avatar);

    void deleteAvatarById(String id);

    List<Avatar> getAllAvatars();

}
