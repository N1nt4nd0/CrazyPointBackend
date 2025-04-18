package ru.feodorkek.dev.crazypoint.config.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import ru.feodorkek.dev.crazypoint.controller.rest.RestControllerPackage;
import ru.feodorkek.dev.crazypoint.dto.ExceptionDtoOut;

@Slf4j
@ControllerAdvice(basePackageClasses = RestControllerPackage.class)
public class RestControllerExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ExceptionDtoOut> handleException(final Exception exception) {
        log.error("RestController error occurred", exception);
        return ResponseEntity.internalServerError().body(new ExceptionDtoOut(exception.getMessage()));
    }

}
