package ru.feodorkek.dev.crazypoint.config.data;

import lombok.RequiredArgsConstructor;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.ExchangeBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
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
    public TopicExchange bigoUserEventsExchange() {
        return ExchangeBuilder.topicExchange(bindingProperties.getBigoUserEventsExchangeName())
                .durable(false)
                .autoDelete()
                .build();
    }

    @Bean
    public Queue bigoUserStartStreamQueue() {
        return QueueBuilder.nonDurable(bindingProperties.getBigoUserStartStreamQueueName())
                .autoDelete()
                .build();
    }

    @Bean
    public Queue bigoUserEndStreamQueue() {
        return QueueBuilder.nonDurable(bindingProperties.getBigoUserEndStreamQueueName())
                .autoDelete()
                .build();
    }

    @Bean
    public Binding bindBigoUserStartStreamQueue(final TopicExchange bigoUserEventsExchange,
                                                final Queue bigoUserStartStreamQueue) {
        return BindingBuilder.bind(bigoUserStartStreamQueue)
                .to(bigoUserEventsExchange)
                .with(bindingProperties.getBigoUserStartStreamRoutingKey());
    }

    @Bean
    public Binding bindBigoUserEndStreamQueue(final TopicExchange bigoUserEventsExchange,
                                              final Queue bigoUserEndStreamQueue) {
        return BindingBuilder.bind(bigoUserEndStreamQueue)
                .to(bigoUserEventsExchange)
                .with(bindingProperties.getBigoUserEndStreamRoutingKey());
    }

    @Bean
    public MessageConverter rabbitMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public RabbitTemplate rabbitTemplate(final ConnectionFactory connectionFactory,
                                         final MessageConverter rabbitMessageConverter) {
        final var rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(rabbitMessageConverter);
        return rabbitTemplate;
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
