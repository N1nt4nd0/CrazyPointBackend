package ru.feodorkek.dev.crazypoint.business;

import ru.feodorkek.dev.crazypoint.dto.StringResultDtoOut;

public interface StringUnitUseCases {

    StringResultDtoOut removeAllDashes(String inputString);

    StringResultDtoOut toUpperCase(String inputString);

    StringResultDtoOut reverse(String inputString);

}
