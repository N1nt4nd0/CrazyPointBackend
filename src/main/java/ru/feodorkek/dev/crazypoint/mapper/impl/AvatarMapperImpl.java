package ru.feodorkek.dev.crazypoint.mapper.impl;

import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.dto.AvatarDtoOut;
import ru.feodorkek.dev.crazypoint.entity.Avatar;
import ru.feodorkek.dev.crazypoint.mapper.AvatarMapper;

@Component
public class AvatarMapperImpl implements AvatarMapper {

    @Override
    public AvatarDtoOut toAvatarDtoOut(final Avatar avatar) {
        return new AvatarDtoOut(avatar.getId().toHexString(), avatar.getAvatarUrl());
    }

}
