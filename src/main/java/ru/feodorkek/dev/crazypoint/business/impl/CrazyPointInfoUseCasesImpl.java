package ru.feodorkek.dev.crazypoint.business.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.feodorkek.dev.crazypoint.business.CrazyPointInfoUseCases;
import ru.feodorkek.dev.crazypoint.config.properties.DonateInfoProperties;
import ru.feodorkek.dev.crazypoint.config.properties.SocialLinksProperties;
import ru.feodorkek.dev.crazypoint.dto.DonateInfoDtoOut;
import ru.feodorkek.dev.crazypoint.dto.SocialLinksDtoOut;
import ru.feodorkek.dev.crazypoint.mapper.DonateInfoMapper;
import ru.feodorkek.dev.crazypoint.mapper.SocialLinksMapper;

@Service
@RequiredArgsConstructor
public class CrazyPointInfoUseCasesImpl implements CrazyPointInfoUseCases {

    private final SocialLinksProperties socialLinksProperties;
    private final DonateInfoProperties donateInfoProperties;
    private final SocialLinksMapper socialLinksMapper;
    private final DonateInfoMapper donateInfoMapper;

    @Override
    public DonateInfoDtoOut getDonateInfo() {
        return donateInfoMapper.toDonateInfoDtoOut(donateInfoProperties);
    }

    @Override
    public SocialLinksDtoOut getSocialLinks() {
        return socialLinksMapper.toSocialLinksDtoOut(socialLinksProperties);
    }

}
