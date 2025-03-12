package ru.feodorkek.dev.crazypoint.util;

import ru.feodorkek.dev.crazypoint.exception.RandomGeneratorException;

import java.util.Random;

public class ExceptionGenerator {

    private final int exceptionProbability;
    private final Random random;

    public ExceptionGenerator(final int exceptionProbability) {
        this.exceptionProbability = exceptionProbability;
        this.random = new Random();
    }

    public void generateRandomException() {
        if (random.nextInt(100) < exceptionProbability) {
            throw new RandomGeneratorException();
        }
    }

}
