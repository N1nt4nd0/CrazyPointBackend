package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.service.AvatarService;

import java.net.URI;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class AvatarServiceImpl implements AvatarService {

    private final List<String> avatarPaths = new ArrayList<>();
    private final ResourceLoader resourceLoader;

    private List<String> readAvatarPathsFromResource(final Resource avatarsResource) throws Exception {
        final var avatarsUri = avatarsResource.getURI();
        if (avatarsUri.getScheme().equals("jar")) {
            return readAvatarPathsFromJar(avatarsUri);
        } else {
            return readPathsFromDirectory(Paths.get(avatarsUri));
        }
    }

    private List<String> readAvatarPathsFromJar(final URI avatarsUri) throws Exception {
        try (final var fs = FileSystems.newFileSystem(avatarsUri, Collections.emptyMap())) {
            return readPathsFromDirectory(fs.getPath("/static/images/avatars/"));
        }
    }

    private List<String> readPathsFromDirectory(final Path avatarsPath) throws Exception {
        try (final var paths = Files.walk(avatarsPath)) {
            return paths
                    .filter(Files::isRegularFile)
                    .map(Path::getFileName)
                    .map(Path::toString)
                    .map(filename -> "/images/avatars/" + filename)
                    .toList();
        }
    }

    @Override
    public List<String> getAvatarPaths() {
        return Collections.unmodifiableList(avatarPaths);
    }

    @Override
    public void readAvatarPaths() {
        final var avatarsResource = resourceLoader.getResource("classpath:/static/images/avatars/");
        try {
            avatarPaths.clear();
            avatarPaths.addAll(readAvatarPathsFromResource(avatarsResource));
        } catch (final Exception exception) {
            log.error("Can't load avatar paths. Return empty list", exception);
        }
    }

    @Override
    public String postConstructProviderName() {
        return "Avatar Service";
    }

    @Override
    public void postConstruct() {
        readAvatarPaths();
    }

}
