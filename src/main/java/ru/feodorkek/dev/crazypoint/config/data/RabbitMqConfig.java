package ru.feodorkek.dev.crazypoint.config.data;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.crazypoint.config.properties.RabbitMqBindingProperties;
import ru.feodorkek.dev.crazypoint.init.PostConstructProvider;

@Configuration
@RequiredArgsConstructor
public class RabbitMqConfig implements PostConstructProvider {

    private final RabbitMqBindingProperties bindingProperties;
    private final ConnectionFactory connectionFactory;

    @Bean
    public FanoutExchange bigoUserStartStreamExchange() {
        return new FanoutExchange(bindingProperties.getBigoUserStartStreamEventExchangeName());
    }

    @Bean
    public FanoutExchange bigoUserEndStreamExchange() {
        return new FanoutExchange(bindingProperties.getBigoUserEndStreamEventExchangeName());
    }

    @Bean
    public MessageConverter rabbitMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Override
    public String postConstructProviderName() {
        return "Rabbit MQ Config";
    }

    @Override
    public void postConstruct() {
        try {
            connectionFactory.createConnection().close();
        } catch (final Exception exception) {
            throw new IllegalStateException("Can't connect to Rabbit MQ", exception);
        }
    }

}
