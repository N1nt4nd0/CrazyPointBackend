package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;
import ru.feodorkek.dev.crazypoint.exception.BigoUserException;
import ru.feodorkek.dev.crazypoint.exception.BigoUserStreamException;
import ru.feodorkek.dev.crazypoint.repository.BigoUserRepository;
import ru.feodorkek.dev.crazypoint.service.BigoUserService;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BigoUserServiceImpl implements BigoUserService {

    private final BigoUserRepository repository;

    @Override
    public BigoUser createNewUser(final String siteId,
                                  final String displayName,
                                  final String timeZone,
                                  final String startStreamMessage,
                                  final String endStreamMessage,
                                  final boolean showStreamMessages) {
        if (repository.findBySiteId(siteId).isPresent()) {
            throw new BigoUserException("Bigo User already exists with siteId: " + siteId);
        }
        return saveUser(new BigoUser(null, siteId, displayName, timeZone,
                startStreamMessage, endStreamMessage, showStreamMessages, null));
    }

    @Override
    public BigoUser updateUser(final String siteId,
                               final String displayName,
                               final String timeZone,
                               final String startStreamMessage,
                               final String endStreamMessage,
                               final boolean showStreamMessages) {
        return saveUser(getUserBySiteId(siteId)
                .withDisplayName(displayName)
                .withTimeZone(timeZone)
                .withStartStreamMessage(startStreamMessage)
                .withEndStreamMessage(endStreamMessage)
                .withShowStreamMessages(showStreamMessages));
    }

    @Override
    public BigoUser updateShowStreamMessages(final String siteId, final boolean showStreamMessages) {
        return saveUser(getUserBySiteId(siteId).withShowStreamMessages(showStreamMessages));
    }

    @Override
    public BigoUser getUserBySiteId(final String siteId) {
        return repository.findBySiteId(siteId)
                .orElseThrow(() -> new BigoUserStreamException("Can't find user by siteId"));
    }

    @Override
    public void deleteUserBySiteId(final String siteId) {
        deleteUser(getUserBySiteId(siteId));
    }

    @Override
    public BigoUser saveUser(final BigoUser bigoUser) {
        return repository.save(bigoUser);
    }

    @Override
    public void deleteUser(final BigoUser bigoUser) {
        repository.delete(bigoUser);
    }

    @Override
    public List<BigoUser> getAllUsers() {
        return repository.findAll();
    }

}
