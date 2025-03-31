package ru.feodorkek.dev.crazypoint.config;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import ru.feodorkek.dev.crazypoint.bot.telegram.AbstractTelegramBot;
import ru.feodorkek.dev.crazypoint.init.PostConstructProvider;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TelegramBotRegistryConfig implements PostConstructProvider {

    private final List<AbstractTelegramBot> telegramBots;

    private void registerBot(final TelegramLongPollingBot bot) {
        try {
            final var telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
            telegramBotsApi.registerBot(bot);
            log.info("Telegram bot '{}' successfully registered", bot.getBotUsername());
        } catch (final Exception exception) {
            log.error("Can't register bot with name: {}", bot.getBotUsername(), exception);
        }
    }

    private void registerTelegramBots() {
        for (final AbstractTelegramBot bot : telegramBots) {
            registerBot(bot);
        }
    }

    @Override
    public String postConstructProviderName() {
        return "Telegram Bot Registry Config";
    }

    @Override
    public void postConstruct() {
        registerTelegramBots();
    }

}
