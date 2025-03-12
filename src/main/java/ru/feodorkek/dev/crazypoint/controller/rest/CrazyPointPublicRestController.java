package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.crazypoint.business.CrazyPointInfoUseCases;
import ru.feodorkek.dev.crazypoint.dto.DonateInfoDtoOut;
import ru.feodorkek.dev.crazypoint.dto.SocialLinksDtoOut;

@Tag(name = "CrazyPoint public usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CrazyPointPublicRestController {

    private final CrazyPointInfoUseCases infoUseCases;

    @Operation(summary = "Ping endpoint")
    @GetMapping("${crazypoint.web.rest.endpoints.public.ping}")
    public ResponseEntity<String> ping() {
        return ResponseEntity.ok("pong");
    }

    @Operation(summary = "Get donate info")
    @GetMapping("${crazypoint.web.rest.endpoints.public.donate-info}")
    public ResponseEntity<DonateInfoDtoOut> getDonateInfo() {
        return ResponseEntity.ok(infoUseCases.getDonateInfo());
    }

    @Operation(summary = "Get social links")
    @GetMapping("${crazypoint.web.rest.endpoints.public.social-links}")
    public ResponseEntity<SocialLinksDtoOut> getSocialLinks() {
        return ResponseEntity.ok(infoUseCases.getSocialLinks());
    }

}
