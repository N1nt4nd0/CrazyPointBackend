package ru.feodorkek.dev.crazypoint.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.support.DefaultConversionService;
import ru.feodorkek.dev.crazypoint.config.converters.DateTimeFormatterConverter;
import ru.feodorkek.dev.crazypoint.config.converters.PatternConverter;
import ru.feodorkek.dev.crazypoint.config.converters.ZoneIdConverter;

@Configuration
@RequiredArgsConstructor
public class PropertiesConverterConfig {

    private final DateTimeFormatterConverter dateTimeFormatterConverter;
    private final PatternConverter patternConverter;
    private final ZoneIdConverter zoneIdConverter;

    @Bean
    public DefaultConversionService defaultConversionService() {
        return new DefaultConversionService();
    }

    @Bean
    public ConversionService conversionService(final DefaultConversionService defaultConversionService) {
        defaultConversionService.addConverter(dateTimeFormatterConverter);
        defaultConversionService.addConverter(patternConverter);
        defaultConversionService.addConverter(zoneIdConverter);
        return defaultConversionService;
    }

}
