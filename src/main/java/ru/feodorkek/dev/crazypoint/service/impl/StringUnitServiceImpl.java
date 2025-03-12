package ru.feodorkek.dev.crazypoint.service.impl;

import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.service.StringUnitService;

@Service
public class StringUnitServiceImpl implements StringUnitService {

    @Override
    public String removeAllDashes(final String inputString) {
        return inputString.replaceAll("-", "");
    }

    @Override
    public String toUpperCase(final String inputString) {
        return inputString.toUpperCase();
    }

    @Override
    public String reverse(final String inputString) {
        return new StringBuilder(inputString).reverse().toString();
    }

}
