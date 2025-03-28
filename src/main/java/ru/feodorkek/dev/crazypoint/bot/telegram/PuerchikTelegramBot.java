package ru.feodorkek.dev.crazypoint.bot.telegram;

import org.springframework.stereotype.Component;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.feodorkek.dev.crazypoint.config.properties.PuerchikTelegramBotProperties;

@Component
public class PuerchikTelegramBot extends AbstractTelegramBot {

    private final PuerchikTelegramBotProperties botProperties;

    public PuerchikTelegramBot(final PuerchikTelegramBotProperties botProperties) {
        super(botProperties.getToken(), botProperties.getName(),
                botProperties.getStartCommandMessage(), botProperties.getAdminUserIds());
        this.botProperties = botProperties;
    }

    public void sendTextToMainOutputChat(final String text) {
        sendText(text, botProperties.getMainOutputChatId());
    }

    @Override
    public void onUpdate(final Update update) {
    }

    @Override
    public void onTextMessage(final Message message, final String text, final Long chatId, boolean isAdmin) {
    }

}
