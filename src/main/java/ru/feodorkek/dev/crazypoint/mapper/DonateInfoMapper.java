package ru.feodorkek.dev.crazypoint.mapper;

import ru.feodorkek.dev.crazypoint.config.properties.DonateInfoProperties;
import ru.feodorkek.dev.crazypoint.dto.DonateInfoDtoOut;

public interface DonateInfoMapper {

    DonateInfoDtoOut toDonateInfoDtoOut(DonateInfoProperties properties);

}
