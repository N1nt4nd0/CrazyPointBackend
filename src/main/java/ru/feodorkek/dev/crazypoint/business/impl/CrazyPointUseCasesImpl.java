package ru.feodorkek.dev.crazypoint.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.business.CrazyPointUseCases;
import ru.feodorkek.dev.crazypoint.dto.AvatarsDtoOut;
import ru.feodorkek.dev.crazypoint.entity.Avatar;
import ru.feodorkek.dev.crazypoint.service.AvatarService;

@Service
@RequiredArgsConstructor
public class CrazyPointUseCasesImpl implements CrazyPointUseCases {

    private final AvatarService avatarService;

    @Override
    public AvatarsDtoOut getAvatars() {
        final var avatarUrls = avatarService.getAllAvatars().stream()
                .map(Avatar::getAvatarUrl)
                .toList();
        return new AvatarsDtoOut(avatarUrls, avatarUrls.size());
    }
}
