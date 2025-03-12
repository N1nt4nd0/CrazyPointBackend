package ru.feodorkek.dev.crazypoint.exception;

public class BigoUserStreamException extends RuntimeException {

    public BigoUserStreamException(final String message) {
        super(message);
    }

    public BigoUserStreamException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
