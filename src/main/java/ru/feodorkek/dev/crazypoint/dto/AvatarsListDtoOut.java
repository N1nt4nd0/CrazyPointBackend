package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

import java.util.List;

@Data
public class AvatarsListDtoOut {

    private final List<AvatarDtoOut> avatars;
    private final int totalAvatars;

}
