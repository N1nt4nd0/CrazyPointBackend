package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.crazypoint.business.CrazyPointUseCases;
import ru.feodorkek.dev.crazypoint.dto.AvatarDtoOut;

@Tag(name = "CrazyPoint private usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class CrazyPointPrivateRestController {

    private final CrazyPointUseCases crazyPointUseCases;

    @Operation(summary = "Create new avatar")
    @PutMapping("${crazypoint.web.rest.endpoints.private.avatar-create}")
    public ResponseEntity<AvatarDtoOut> createNewAvatar(@RequestParam final String avatarUrl) {
        return ResponseEntity.ok(crazyPointUseCases.createNewAvatar(avatarUrl));
    }

    @Operation(summary = "Delete avatar")
    @DeleteMapping("${crazypoint.web.rest.endpoints.private.avatar-delete}")
    public ResponseEntity<Void> deleteAvatar(@RequestParam final String avatarId) {
        crazyPointUseCases.deleteAvatar(avatarId);
        return ResponseEntity.ok().build();
    }

}
