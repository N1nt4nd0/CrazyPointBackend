package ru.feodorkek.dev.crazypoint.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.bot.telegram.PuerchikTelegramBot;
import ru.feodorkek.dev.crazypoint.event.BigoUserEndStreamEvent;
import ru.feodorkek.dev.crazypoint.event.BigoUserEventHandler;
import ru.feodorkek.dev.crazypoint.event.BigoUserStartStreamEvent;
import ru.feodorkek.dev.crazypoint.service.DateTimeService;

@Component
@RequiredArgsConstructor
public class PuerTgBotBigoUserEventHandler implements BigoUserEventHandler {

    private final PuerchikTelegramBot puerchikTelegramBot;
    private final DateTimeService dateTimeService;

    @Override
    public void onStartBigoStream(final BigoUserStartStreamEvent event) {
        if (event.isShowStreamMessage()) {
            puerchikTelegramBot.sendTextToMainOutputChat(String.format("""
                            📣 <b>BigoLive TV:</b> %s
                            <b>Тема:</b> %s
                            
                            <b>Ссылка:</b> %s
                            """,
                    event.getStartStreamMessage(),
                    event.hasRoomTopic() ? event.getRoomTopic() : "без темы",
                    event.getUserLink()));
        }
    }

    @Override
    public void onEndBigoStream(final BigoUserEndStreamEvent event) {
        if (event.isShowStreamMessage()) {
            puerchikTelegramBot.sendTextToMainOutputChat(String.format("""
                            📣 <b>BigoLive TV:</b> %s
                            
                            <b>Стрим длился:</b> %s
                            """,
                    event.getEndStreamMessage(),
                    dateTimeService.instantDifferenceToHoursMinutesSecondsFormat(
                            event.getStartStreamTime(), event.getEndStreamTime())));
        }
    }

}
