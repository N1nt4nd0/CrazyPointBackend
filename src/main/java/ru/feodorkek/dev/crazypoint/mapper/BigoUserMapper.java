package ru.feodorkek.dev.crazypoint.mapper;

import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;
import ru.feodorkek.dev.crazypoint.event.BigoUserEndStreamEvent;
import ru.feodorkek.dev.crazypoint.event.BigoUserStartStreamEvent;

public interface BigoUserMapper {

    BigoUserDtoOut toBigoUserDtoOut(BigoUser bigoUser);

    BigoUserStartStreamEvent toBigoUserStartStreamEvent(BigoUser bigoUser);

    BigoUserEndStreamEvent toBigoUserEndStreamEvent(BigoUser bigoUser);

}
