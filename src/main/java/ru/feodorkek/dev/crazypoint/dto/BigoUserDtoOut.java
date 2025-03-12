package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;

@Data
public class BigoUserDtoOut {

    private final String siteId;
    private final String userLink;
    private final String displayName;
    private final String timeZone;
    private final String startStreamMessage;
    private final String endStreamMessage;
    private final boolean streamingNow;
    private final boolean showStreamMessages;

}
