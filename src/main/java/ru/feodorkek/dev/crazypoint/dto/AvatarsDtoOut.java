package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

import java.util.List;

@Data
public class AvatarsDtoOut {

    private final String defAvatarPath;
    private final List<String> avatarPaths;

}
