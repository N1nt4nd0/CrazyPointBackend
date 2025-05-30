package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.crazypoint.business.BigoUserUseCases;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoIn;
import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;

@Tag(name = "BigoUser private usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class BigoLivePrivateRestController {

    private final BigoUserUseCases bigoUserUseCases;

    @Operation(summary = "Create new user")
    @PutMapping("${crazypoint.web.rest.endpoints.private.bigo-user-create}")
    public ResponseEntity<BigoUserDtoOut> createNewUser(
            @Valid @RequestBody final BigoUserDtoIn bigoUserDtoIn) {
        return ResponseEntity.ok(bigoUserUseCases.createNewUser(bigoUserDtoIn));
    }

    @Operation(summary = "Update user")
    @PatchMapping("${crazypoint.web.rest.endpoints.private.bigo-user-update}")
    public ResponseEntity<BigoUserDtoOut> updateUser(
            @Valid @RequestBody final BigoUserDtoIn bigoUserDtoIn) {
        return ResponseEntity.ok(bigoUserUseCases.updateUser(bigoUserDtoIn));
    }

    @Operation(summary = "Delete user")
    @DeleteMapping("${crazypoint.web.rest.endpoints.private.bigo-user-delete}")
    public ResponseEntity<Void> deleteUser(@RequestParam final String siteId) {
        bigoUserUseCases.deleteUserBySiteId(siteId);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Update stream messages is showing")
    @PatchMapping("${crazypoint.web.rest.endpoints.private.bigo-user-show-stream-message}")
    public ResponseEntity<BigoUserDtoOut> updateShowStreamMessage(
            @RequestParam final String siteId,
            @RequestParam final boolean showStreamMessages) {
        return ResponseEntity.ok(bigoUserUseCases.updateShowStreamMessages(siteId, showStreamMessages));
    }

    @Operation(summary = "Process start bigo stream manually")
    @PostMapping("${crazypoint.web.rest.endpoints.private.bigo-user-start-stream}")
    public ResponseEntity<Void> startBigoStream(
            @RequestParam final String siteId,
            @RequestParam final String roomTopic,
            @RequestParam final String streamUrl) {
        bigoUserUseCases.startBigoStream(siteId, roomTopic, streamUrl);
        return ResponseEntity.ok().build();
    }

    @Operation(summary = "Process end bigo stream manually")
    @PostMapping("${crazypoint.web.rest.endpoints.private.bigo-user-end-stream}")
    public ResponseEntity<Void> endBigoStream(@RequestParam final String siteId) {
        bigoUserUseCases.endBigoStream(siteId);
        return ResponseEntity.ok().build();
    }

}
