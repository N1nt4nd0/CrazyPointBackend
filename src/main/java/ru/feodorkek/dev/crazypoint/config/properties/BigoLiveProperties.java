package ru.feodorkek.dev.crazypoint.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("crazypoint.bigo-live")
public class BigoLiveProperties {

    private boolean userUpdatesCheckEnabled;
    private String userUpdatesApiUrl;
    private int userUpdatesCheckDelayMs;
    private String userLinkPrefix;

    public String formatUserLink(final String siteId) {
        return String.format("%s%s", userLinkPrefix, siteId);
    }

}
