package ru.feodorkek.dev.crazypoint.controller.rest;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.feodorkek.dev.crazypoint.business.BigoStreamChartUseCases;
import ru.feodorkek.dev.crazypoint.business.BigoUserUseCases;
import ru.feodorkek.dev.crazypoint.dto.BigoOfficialUserInfo;
import ru.feodorkek.dev.crazypoint.dto.BigoStreamChartDailyDtoOut;
import ru.feodorkek.dev.crazypoint.dto.BigoStreamDaysDtoOut;
import ru.feodorkek.dev.crazypoint.service.DateTimeService;

import java.time.LocalDate;

@Tag(name = "BigoUser public usages")
@CrossOrigin
@RestController
@RequiredArgsConstructor
public class BigoLivePublicRestController {

    private final BigoStreamChartUseCases bigoStreamChartUseCases;
    private final BigoUserUseCases bigoUserUseCases;
    private final DateTimeService dateTimeService;

    @Operation(summary = "Get official bigo user info")
    @GetMapping("${crazypoint.web.rest.endpoints.public.bigo-official-user-info}")
    public ResponseEntity<BigoOfficialUserInfo> fetchOfficialBigoUserInfo(@RequestParam final String siteId) {
        return ResponseEntity.ok(bigoUserUseCases.fetchBigoOfficialUserInfo(siteId));
    }

    @Operation(summary = "Get stream chart daily data")
    @GetMapping("${crazypoint.web.rest.endpoints.public.bigo-user-stream-chart-daily-data}")
    public ResponseEntity<BigoStreamChartDailyDtoOut> getBigoStreamChartDailyData(
            @RequestParam final String siteId,
            @RequestParam(required = false) final String date) {
        final var forDate = LocalDate.parse(date, dateTimeService.daysMonthYearFormatter());
        return ResponseEntity.ok(bigoStreamChartUseCases.getChartDailyData(siteId, forDate));
    }

    @Operation(summary = "Get stream days")
    @GetMapping("${crazypoint.web.rest.endpoints.public.bigo-user-stream-days-data}")
    public ResponseEntity<BigoStreamDaysDtoOut> getBigoStreamDaysData(
            @RequestParam final String siteId) {
        return ResponseEntity.ok(bigoStreamChartUseCases.getStreamDaysData(siteId));
    }

}
