package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.init.PostConstructProvider;

import java.util.List;

public interface AvatarService extends PostConstructProvider {

    List<String> getAvatarPaths();

    void readAvatarPaths();

}
