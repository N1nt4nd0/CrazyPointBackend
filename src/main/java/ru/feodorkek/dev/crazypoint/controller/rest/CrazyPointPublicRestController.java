package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.crazypoint.business.CrazyPointUseCases;
import ru.feodorkek.dev.crazypoint.dto.AvatarsListDtoOut;

@Tag(name = "CrazyPoint public usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CrazyPointPublicRestController {

    private final CrazyPointUseCases crazyPointUseCases;

    @Operation(summary = "Ping endpoint")
    @GetMapping("${crazypoint.web.rest.endpoints.public.ping}")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @Operation(summary = "Get avatars")
    @GetMapping("${crazypoint.web.rest.endpoints.public.avatars-list}")
    public ResponseEntity<AvatarsListDtoOut> getAvatars() {
        return ResponseEntity.ok(crazyPointUseCases.getAvatars());
    }

}
