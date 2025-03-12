package ru.feodorkek.dev.crazypoint.config.converters;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.format.DateTimeFormatter;

@Component
public class DateTimeFormatterConverter implements Converter<String, DateTimeFormatter> {

    @Override
    public DateTimeFormatter convert(@NonNull final String dateTimePattern) {
        return DateTimeFormatter.ofPattern(dateTimePattern);
    }

}