package ru.feodorkek.dev.crazypoint.mapper.impl;

import org.springframework.stereotype.Component;
import ru.feodorkek.dev.crazypoint.config.properties.SocialLinksProperties;
import ru.feodorkek.dev.crazypoint.dto.SocialLinksDtoOut;
import ru.feodorkek.dev.crazypoint.mapper.SocialLinksMapper;

@Component
public class SocialLinksMapperImpl implements SocialLinksMapper {

    @Override
    public SocialLinksDtoOut toSocialLinksDtoOut(final SocialLinksProperties properties) {
        return new SocialLinksDtoOut(
                properties.getInstagram().isEnabled(),
                properties.getInstagram().getTitle(),
                properties.getInstagram().getLink(),
                properties.getTelegram().isEnabled(),
                properties.getTelegram().getTitle(),
                properties.getTelegram().getLink(),
                properties.getTiktok().isEnabled(),
                properties.getTiktok().getTitle(),
                properties.getTiktok().getLink(),
                properties.getBigoLive().isEnabled(),
                properties.getBigoLive().getTitle(),
                properties.getBigoLive().getLink());
    }

}
