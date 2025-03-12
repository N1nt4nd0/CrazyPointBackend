package ru.feodorkek.dev.crazypoint.dto.validation;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

import java.time.ZoneId;

public class TimeZoneValidator implements ConstraintValidator<ValidTimeZone, String> {

    @Override
    public boolean isValid(final String value, final ConstraintValidatorContext context) {
        try {
            ZoneId.of(value);
            return true;
        } catch (final Exception exception) {
            return false;
        }
    }

}
