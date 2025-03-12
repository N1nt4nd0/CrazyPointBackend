package ru.feodorkek.dev.crazypoint.config.converters;

import lombok.NonNull;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import java.time.ZoneId;

@Component
public class ZoneIdConverter implements Converter<String, ZoneId> {

    @Override
    public ZoneId convert(@NonNull final String zoneIdName) {
        return ZoneId.of(zoneIdName);
    }

}