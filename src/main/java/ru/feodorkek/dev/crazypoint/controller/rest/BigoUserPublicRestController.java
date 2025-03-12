package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.crazypoint.business.BigoUserUseCases;
import ru.feodorkek.dev.crazypoint.dto.BigoOfficialUserInfo;

@Tag(name = "BigoUser public usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class BigoUserPublicRestController {

    private final BigoUserUseCases bigoUserUseCases;

    @Operation(summary = "Get official bigo user info")
    @GetMapping("${crazypoint.web.rest.endpoints.public.bigo-official-user-info}")
    public ResponseEntity<BigoOfficialUserInfo> fetchOfficialBigoUserInfo(@RequestParam final String siteId) {
        return ResponseEntity.ok(bigoUserUseCases.fetchBigoOfficialUserInfo(siteId));
    }

}
