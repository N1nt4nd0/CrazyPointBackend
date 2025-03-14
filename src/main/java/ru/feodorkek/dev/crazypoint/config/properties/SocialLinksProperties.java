package ru.feodorkek.dev.crazypoint.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties("crazypoint.social-links")
public class SocialLinksProperties {

    private Instagram instagram;
    private Telegram telegram;
    private BigoLive bigoLive;
    private Tiktok tiktok;

    @Data
    public static class Instagram {
        private boolean enabled;
        private String title;
        private String link;
    }

    @Data
    public static class Telegram {
        private boolean enabled;
        private String title;
        private String link;
    }

    @Data
    public static class Tiktok {
        private boolean enabled;
        private String title;
        private String link;
    }

    @Data
    public static class BigoLive {
        private boolean enabled;
        private String title;
        private String link;
    }

}
