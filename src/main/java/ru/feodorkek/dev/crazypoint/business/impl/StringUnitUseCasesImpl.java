package ru.feodorkek.dev.crazypoint.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.business.StringUnitUseCases;
import ru.feodorkek.dev.crazypoint.dto.StringResultDtoOut;
import ru.feodorkek.dev.crazypoint.service.StringUnitService;
import ru.feodorkek.dev.crazypoint.util.ExceptionGenerator;

@Service
@RequiredArgsConstructor
public class StringUnitUseCasesImpl implements StringUnitUseCases {

    private final ExceptionGenerator stringUnitExceptionGenerator;
    private final StringUnitService stringUnitService;

    @Override
    public StringResultDtoOut removeAllDashes(final String inputString) {
        stringUnitExceptionGenerator.generateRandomException();
        return new StringResultDtoOut(stringUnitService.removeAllDashes(inputString));
    }

    @Override
    public StringResultDtoOut toUpperCase(final String inputString) {
        stringUnitExceptionGenerator.generateRandomException();
        return new StringResultDtoOut(stringUnitService.toUpperCase(inputString));
    }

    @Override
    public StringResultDtoOut reverse(final String inputString) {
        stringUnitExceptionGenerator.generateRandomException();
        return new StringResultDtoOut(stringUnitService.reverse(inputString));
    }

}
