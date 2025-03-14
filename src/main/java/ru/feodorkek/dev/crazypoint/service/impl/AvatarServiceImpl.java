package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import org.bson.types.ObjectId;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.entity.Avatar;
import ru.feodorkek.dev.crazypoint.repository.AvatarRepository;
import ru.feodorkek.dev.crazypoint.service.AvatarService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final AvatarRepository avatarRepository;

    @Override
    public Avatar createNewAvatar(final String avatarUrl) {
        return saveAvatar(new Avatar(null, avatarUrl));
    }

    @Override
    public Avatar saveAvatar(final Avatar avatar) {
        return avatarRepository.save(avatar);
    }

    @Override
    public void deleteAvatarById(final String id) {
        avatarRepository.deleteById(new ObjectId(id));
    }

    @Override
    public List<Avatar> getAllAvatars() {
        return avatarRepository.findAll();
    }

}
