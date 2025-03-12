package ru.feodorkek.dev.crazypoint.exception;

public class BigoLiveApiException extends RuntimeException {

    public BigoLiveApiException(final String message) {
        super(message);
    }

    public BigoLiveApiException(final String message, final Throwable cause) {
        super(message, cause);
    }

}
