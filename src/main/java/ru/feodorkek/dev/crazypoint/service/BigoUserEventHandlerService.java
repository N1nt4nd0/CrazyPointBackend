package ru.feodorkek.dev.crazypoint.service;

import ru.feodorkek.dev.crazypoint.event.BigoUserEndStreamEvent;
import ru.feodorkek.dev.crazypoint.event.BigoUserStartStreamEvent;

public interface BigoUserEventHandlerService {

    void postStartStreamEvent(BigoUserStartStreamEvent event);

    void postEndStreamEvent(BigoUserEndStreamEvent event);

}
