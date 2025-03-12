package ru.feodorkek.dev.crazypoint.mapper.impl;

import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.config.properties.DonateInfoProperties;
import ru.feodorkek.dev.crazypoint.dto.DonateInfoDtoOut;
import ru.feodorkek.dev.crazypoint.mapper.DonateInfoMapper;

@Component
public class DonateInfoMapperImpl implements DonateInfoMapper {

    @Override
    public DonateInfoDtoOut toDonateInfoDtoOut(final DonateInfoProperties properties) {
        return new DonateInfoDtoOut(
                properties.getDonateLink(),
                properties.getBelarusBank().getTitle(),
                properties.getBelarusBank().getCard(),
                properties.getBelarusBank().getIban(),
                properties.getSberBank().getTitle(),
                properties.getSberBank().getCard(),
                properties.getSberBank().getIban(),
                properties.getPkoBank().getTitle(),
                properties.getPkoBank().getCard(),
                properties.getPkoBank().getAccountNumber(),
                properties.getPkoBank().getIban(),
                properties.getYooMoney().getTitle(),
                properties.getYooMoney().getWallet(),
                properties.getUsdt().getTitle(),
                properties.getUsdt().getAddress());
    }

}
