package ru.feodorkek.dev.crazypoint.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.business.CrazyPointUseCases;
import ru.feodorkek.dev.crazypoint.dto.AvatarDtoOut;
import ru.feodorkek.dev.crazypoint.dto.AvatarsListDtoOut;
import ru.feodorkek.dev.crazypoint.mapper.AvatarMapper;
import ru.feodorkek.dev.crazypoint.service.AvatarService;

import java.util.Collections;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CrazyPointUseCasesImpl implements CrazyPointUseCases {

    private final AvatarService avatarService;
    private final AvatarMapper avatarMapper;

    @Override
    public AvatarsListDtoOut getAvatars() {
        final var avatars = avatarService.getAllAvatars().stream()
                .map(avatarMapper::toAvatarDtoOut)
                .collect(Collectors.toList());
        Collections.shuffle(avatars);
        return new AvatarsListDtoOut(avatars, avatars.size());
    }

    @Override
    public AvatarDtoOut createNewAvatar(final String avatarUrl) {
        return avatarMapper.toAvatarDtoOut(avatarService.createNewAvatar(avatarUrl));
    }

    @Override
    public void deleteAvatar(final String avatarId) {
        avatarService.deleteAvatarById(avatarId);
    }

}
