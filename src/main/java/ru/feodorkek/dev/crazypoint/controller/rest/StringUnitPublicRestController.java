package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.crazypoint.business.StringUnitUseCases;
import ru.feodorkek.dev.crazypoint.dto.StringResultDtoOut;

@Tag(name = "StringUnit public usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class StringUnitPublicRestController {

    private final StringUnitUseCases stringUnitUseCases;

    @Operation(summary = "Remove dashes from input string")
    @GetMapping("${crazypoint.web.rest.endpoints.public.string-remove-dashes}")
    public ResponseEntity<StringResultDtoOut> removeDashes(
            @RequestParam(required = false, defaultValue = "") final String inputString) {
        return ResponseEntity.ok(stringUnitUseCases.removeAllDashes(inputString));
    }

    @Operation(summary = "Make input string to upper case")
    @GetMapping("${crazypoint.web.rest.endpoints.public.string-to-upper-case}")
    public ResponseEntity<StringResultDtoOut> toUpperCase(
            @RequestParam(required = false, defaultValue = "") final String inputString) {
        return ResponseEntity.ok(stringUnitUseCases.toUpperCase(inputString));
    }

    @Operation(summary = "Reverse input string")
    @GetMapping("${crazypoint.web.rest.endpoints.public.string-reverse}")
    public ResponseEntity<StringResultDtoOut> reverse(
            @RequestParam(required = false, defaultValue = "") final String inputString) {
        return ResponseEntity.ok(stringUnitUseCases.reverse(inputString));
    }

}
