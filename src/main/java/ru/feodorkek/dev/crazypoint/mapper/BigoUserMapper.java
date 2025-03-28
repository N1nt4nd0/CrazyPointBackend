package ru.feodorkek.dev.crazypoint.mapper;

import ru.feodorkek.dev.crazypoint.dto.BigoUserDtoOut;
import ru.feodorkek.dev.crazypoint.entity.BigoUser;

public interface BigoUserMapper {

    BigoUserDtoOut toBigoUserDtoOut(BigoUser bigoUser);

}
