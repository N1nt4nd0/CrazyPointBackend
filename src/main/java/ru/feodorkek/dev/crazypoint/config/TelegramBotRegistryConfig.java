package ru.feodorkek.dev.crazypoint.config;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.feodorkek.dev.crazypoint.bot.telegram.PuerchikTelegramBot;

@Slf4j
@Configuration
public class TelegramBotRegistryConfig {

    private TelegramBotsApi registerBot(final TelegramLongPollingBot bot) {
        try {
            final var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
            log.info("Telegram bot '{}' successfully registered", bot.getBotUsername());
            return telegramBotsApi;
        } catch (final Exception exception) {
            log.error("Can't register bot with name: {}", bot.getBotUsername(), exception);
            return null;
        }
    }

    @Bean
    public TelegramBotsApi registerPuerchikBot(final PuerchikTelegramBot puerchikBot) {
        return registerBot(puerchikBot);
    }

}
