package ru.feodorkek.dev.crazypoint.event.impl;

import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.event.BigoUserEndStreamEvent;
import ru.feodorkek.dev.crazypoint.event.BigoUserEventHandler;
import ru.feodorkek.dev.crazypoint.event.BigoUserStartStreamEvent;

@Component
public class RabbitMqBigoUserEventHandler implements BigoUserEventHandler {

    @Override
    public void onStartBigoStream(final BigoUserStartStreamEvent event) {

    }

    @Override
    public void onEndBigoStream(final BigoUserEndStreamEvent event) {

    }

}
