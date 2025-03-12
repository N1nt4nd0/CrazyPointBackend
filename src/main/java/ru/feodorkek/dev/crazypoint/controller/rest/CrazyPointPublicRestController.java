package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "CrazyPoint public usages")
@CrossOrigin
@RestController
public class CrazyPointPublicRestController {

    @Operation(summary = "Ping endpoint")
    @GetMapping("${crazypoint.web.rest.endpoints.public.ping}")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

}
