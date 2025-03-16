package ru.feodorkek.dev.crazypoint.business;

import ru.feodorkek.dev.crazypoint.dto.AvatarDtoOut;
import ru.feodorkek.dev.crazypoint.dto.AvatarsListDtoOut;

public interface CrazyPointUseCases {

    AvatarDtoOut createNewAvatar(String avatarUrl);

    void deleteAvatar(String avatarId);

    AvatarsListDtoOut getAvatars();

}
