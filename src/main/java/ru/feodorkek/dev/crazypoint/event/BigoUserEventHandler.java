package ru.feodorkek.dev.crazypoint.event;

public interface BigoUserEventHandler {

    void onStartBigoStream(BigoUserStartStreamEvent event);

    void onEndBigoStream(BigoUserEndStreamEvent event);

}
