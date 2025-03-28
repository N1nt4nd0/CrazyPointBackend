package ru.feodorkek.dev.crazypoint.config.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Data
@Configuration
@ConfigurationProperties("crazypoint.bot.telegram.puerchik-bot")
public class PuerchikTelegramBotProperties {

    private String token;
    private String name;
    private String startCommandMessage;
    private List<Long> adminUserIds;
    private Long mainOutputChatId;

}
