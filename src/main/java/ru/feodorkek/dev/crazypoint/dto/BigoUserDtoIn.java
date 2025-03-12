package ru.feodorkek.dev.crazypoint.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import ru.feodorkek.dev.crazypoint.dto.validation.ValidTimeZone;

@Data
public class BigoUserDtoIn {

    @NotBlank
    @Size(min = 2, max = 80)
    private final String siteId;

    @NotBlank
    @Size(min = 2, max = 80)
    private final String displayName;

    @NotBlank
    @ValidTimeZone
    private final String timeZone;

    @NotBlank
    @Size(min = 2, max = 200)
    private final String startStreamMessage;

    @NotBlank
    @Size(min = 2, max = 200)
    private final String endStreamMessage;

    private final boolean showStreamMessages;

}
