package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

@Data
public class SocialLinksDtoOut {

    private final boolean instagramEnabled;
    private final String instagramTitle;
    private final String instagramLink;

    private final boolean telegramEnabled;
    private final String telegramTitle;
    private final String telegramLink;

    private final boolean tiktokEnabled;
    private final String tiktokTitle;
    private final String tiktokLink;

    private final boolean bigoLiveEnabled;
    private final String bigoLiveTitle;
    private final String bigoLiveLink;

}
