package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

import java.util.List;

@Data
public class AvatarsDtoOut {

    private final List<String> avatarUrls;
    private final int totalAvatars;

}
