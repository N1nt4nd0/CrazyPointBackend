package ru.feodorkek.dev.crazypoint.dto;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class BigoUserDtoOut {

    private final String siteId;
    private final String userLink;
    private final String displayName;
    private final String timeZone;
    private final String startStreamMessage;
    private final String endStreamMessage;
    private final boolean streamingNow;
    private final boolean showStreamMessages;

    @JsonCreator
    public BigoUserDtoOut(
            @JsonProperty("siteId") String siteId,
            @JsonProperty("userLink") String userLink,
            @JsonProperty("displayName") String displayName,
            @JsonProperty("timeZone") String timeZone,
            @JsonProperty("startStreamMessage") String startStreamMessage,
            @JsonProperty("endStreamMessage") String endStreamMessage,
            @JsonProperty("streamingNow") boolean streamingNow,
            @JsonProperty("showStreamMessages") boolean showStreamMessages) {
        this.showStreamMessages = showStreamMessages;
        this.startStreamMessage = startStreamMessage;
        this.endStreamMessage = endStreamMessage;
        this.streamingNow = streamingNow;
        this.displayName = displayName;
        this.timeZone = timeZone;
        this.userLink = userLink;
        this.siteId = siteId;
    }

}
