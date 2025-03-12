package ru.feodorkek.dev.crazypoint.mapper;

import ru.feodorkek.dev.crazypoint.config.properties.SocialLinksProperties;
import ru.feodorkek.dev.crazypoint.dto.SocialLinksDtoOut;

public interface SocialLinksMapper {

    SocialLinksDtoOut toSocialLinksDtoOut(SocialLinksProperties properties);

}
