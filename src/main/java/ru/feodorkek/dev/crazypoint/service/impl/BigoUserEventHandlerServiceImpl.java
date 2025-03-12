package ru.feodorkek.dev.crazypoint.service.impl;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.event.BigoUserEndStreamEvent;
import ru.feodorkek.dev.crazypoint.event.BigoUserEventHandler;
import ru.feodorkek.dev.crazypoint.event.BigoUserStartStreamEvent;
import ru.feodorkek.dev.crazypoint.service.BigoUserEventHandlerService;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class BigoUserEventHandlerServiceImpl implements BigoUserEventHandlerService {

    private final List<BigoUserEventHandler> handlers;

    @Override
    public void postStartStreamEvent(final BigoUserStartStreamEvent event) {
        for (final BigoUserEventHandler handler : handlers) {
            try {
                handler.onStartBigoStream(event);
            } catch (final Exception exception) {
                log.error("Can't handle start stream event. SiteId: {}, Handler: {}",
                        event.getSiteId(), handler, exception);
            }
        }
    }

    @Override
    public void postEndStreamEvent(final BigoUserEndStreamEvent event) {
        for (final BigoUserEventHandler handler : handlers) {
            try {
                handler.onEndBigoStream(event);
            } catch (final Exception exception) {
                log.error("Can't handle end stream event. SiteId: {}, Handler: {}",
                        event.getSiteId(), handler, exception);
            }
        }
    }

}
