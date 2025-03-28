package ru.feodorkek.dev.crazypoint.bot.telegram;

import lombok.extern.slf4j.Slf4j;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import ru.feodorkek.dev.crazypoint.util.StringUnit;

import java.util.List;

@Slf4j
public abstract class AbstractTelegramBot extends TelegramLongPollingBot {

    private final String startCommandMessage;
    private final List<Long> adminUserIds;
    private final String botName;

    public AbstractTelegramBot(final String botToken,
                               final String botName,
                               final String startCommandMessage,
                               final List<Long> adminUserIds) {
        super(botToken);
        this.startCommandMessage = startCommandMessage;
        this.adminUserIds = adminUserIds;
        this.botName = botName;
    }

    private void processTextMessage(final Message message,
                                    final String text,
                                    final Long chatId,
                                    final boolean isAdmin) {
        switch (text) {
            case "/start":
                if (StringUnit.isNotBlank(startCommandMessage)) {
                    sendText(startCommandMessage, chatId);
                    return;
                }
            case "/debug":
                sendText(buildDebugInfo(message), chatId);
                return;
        }
        onTextMessage(message, text, chatId, isAdmin);
    }

    private String buildDebugInfo(final Message message) {
        final var chatId = message.getChatId();
        final var messageId = message.getMessageId();
        final var text = message.getText();
        final var userId = message.getFrom().getId();
        final var username = message.getFrom().getUserName();
        final var isAdmin = isAdmin(userId);
        return String.format("""
                <b>Chat Id</b>: %s
                <b>Message Id</b>: %s
                <b>Text</b>: %s
                <b>Username</b>: %s
                <b>User Id</b>: %s
                <b>Is Admin</b>: %s
                """, chatId, messageId, text, username, userId, isAdmin);
    }

    private void processTextMessageException(final Exception exception, final Long chatId) {
        sendText(String.format("<b>Error</b>: %s", exception.getMessage()), chatId);
    }

    public boolean isAdmin(final Long userId) {
        return adminUserIds.contains(userId);
    }

    public void sendText(final String text, final Long chatId) {
        executeSendMessage(buildMessage(text, chatId));
    }

    public void executeSendMessage(final SendMessage message) {
        try {
            execute(message);
        } catch (final Exception exception) {
            log.error("Can't execute send message. ChatId: {}, Text: {}",
                    message.getChatId(), message.getText(), exception);
        }
    }

    public SendMessage buildMessage(final String text, final Long chatId) {
        return SendMessage.builder()
                .chatId(chatId)
                .text(text)
                .parseMode(ParseMode.HTML)
                .build();
    }

    @Override
    public String getBotUsername() {
        return botName;
    }

    @Override
    public void onUpdateReceived(final Update update) {
        onUpdate(update);
        if (update.hasMessage() && update.getMessage().hasText()) {
            final var message = update.getMessage();
            final var text = message.getText();
            final var chatId = message.getChatId();
            final var isAdmin = isAdmin(chatId);
            try {
                processTextMessage(message, text, chatId, isAdmin);
            } catch (final Exception exception) {
                processTextMessageException(exception, chatId);
            }
        }
    }

    public abstract void onUpdate(final Update update);

    public abstract void onTextMessage(final Message message,
                                       final String text,
                                       final Long chatId,
                                       final boolean isAdmin);

}
