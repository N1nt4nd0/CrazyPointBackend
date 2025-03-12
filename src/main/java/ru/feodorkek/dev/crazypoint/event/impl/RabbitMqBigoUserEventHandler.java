package ru.feodorkek.dev.crazypoint.event.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.config.properties.RabbitMqBindingProperties;
import ru.feodorkek.dev.crazypoint.event.BigoUserEndStreamEvent;
import ru.feodorkek.dev.crazypoint.event.BigoUserEventHandler;
import ru.feodorkek.dev.crazypoint.event.BigoUserStartStreamEvent;

@Component
@RequiredArgsConstructor
public class RabbitMqBigoUserEventHandler implements BigoUserEventHandler {

    private final RabbitMqBindingProperties bindingProperties;
    private final RabbitTemplate rabbitTemplate;

    @Override
    public void onStartBigoStream(final BigoUserStartStreamEvent event) {
        rabbitTemplate.convertAndSend(bindingProperties.getBigoUserEventsExchangeName(),
                bindingProperties.getBigoUserStartStreamRoutingKey(), event);
    }

    @Override
    public void onEndBigoStream(final BigoUserEndStreamEvent event) {
        rabbitTemplate.convertAndSend(bindingProperties.getBigoUserEventsExchangeName(),
                bindingProperties.getBigoUserEndStreamRoutingKey(), event);
    }

}
