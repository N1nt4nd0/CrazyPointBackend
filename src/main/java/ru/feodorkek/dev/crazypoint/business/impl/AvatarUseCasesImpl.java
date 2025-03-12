package ru.feodorkek.dev.crazypoint.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.business.AvatarUseCases;
import ru.feodorkek.dev.crazypoint.dto.AvatarsDtoOut;
import ru.feodorkek.dev.crazypoint.service.AvatarService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
public class AvatarUseCasesImpl implements AvatarUseCases {

    private final AvatarService avatarService;

    @Override
    public AvatarsDtoOut getAvatars() {
        final var avatarPaths = avatarService.getAvatarPaths();
        if (avatarPaths.isEmpty()) {
            return new AvatarsDtoOut("/images/default_avatar.jpg", List.of());
        } else {
            final var shuffledPaths = new ArrayList<>(avatarPaths);
            Collections.shuffle(shuffledPaths);
            final var defAvatar = shuffledPaths.get(avatarPaths.size() - 1);
            return new AvatarsDtoOut(defAvatar, shuffledPaths);
        }
    }

}
