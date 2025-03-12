package ru.feodorkek.dev.crazypoint.exception;

public class BigoUserException extends RuntimeException {

    public BigoUserException(final String message) {
        super(message);
    }

    public BigoUserException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
