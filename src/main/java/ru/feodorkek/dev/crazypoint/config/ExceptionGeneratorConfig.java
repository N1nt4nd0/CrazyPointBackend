package ru.feodorkek.dev.crazypoint.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import ru.feodorkek.dev.crazypoint.config.properties.ExceptionGeneratorProperties;
import ru.feodorkek.dev.crazypoint.util.ExceptionGenerator;

@Configuration
@RequiredArgsConstructor
public class ExceptionGeneratorConfig {

    private final ExceptionGeneratorProperties generatorProperties;

    @Bean
    public ExceptionGenerator stringUnitExceptionGenerator() {
        return new ExceptionGenerator(generatorProperties.getStringUnitExceptionProbabilityPercent());
    }

}
