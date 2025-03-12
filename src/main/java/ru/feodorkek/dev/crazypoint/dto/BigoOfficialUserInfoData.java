package ru.feodorkek.dev.crazypoint.dto;

import lombok.Data;
import ru.feodorkek.dev.crazypoint.util.StringUnit;

import java.util.List;

@Data
public class BigoOfficialUserInfoData {

    private final long sid;
    private final String siteId;
    private final long uid;
    private final String avatar;
    private final String nick_name;
    private final String country_code;
    private final String gameTitle;
    private final int gameId;
    private final String roomTopic;
    private final String snapshot;
    private final int alive;
    private final String roomId;
    private final int roomStatus;
    private final String clientIp;
    private final String hls_src;
    private final List<String> cdn_src;
    private final boolean passRoom;
    private final int reserver;
    private final String clientBigoId;
    private final String roomType;

    public boolean isStreamingNow() {
        return StringUnit.isNotBlank(hls_src);
    }

}
