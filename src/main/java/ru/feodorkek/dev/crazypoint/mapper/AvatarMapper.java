package ru.feodorkek.dev.crazypoint.mapper;

import ru.feodorkek.dev.crazypoint.dto.AvatarDtoOut;
import ru.feodorkek.dev.crazypoint.entity.Avatar;

public interface AvatarMapper {

    AvatarDtoOut toAvatarDtoOut(Avatar avatar);

}
