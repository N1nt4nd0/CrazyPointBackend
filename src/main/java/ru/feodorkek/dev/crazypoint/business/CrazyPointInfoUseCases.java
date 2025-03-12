package ru.feodorkek.dev.crazypoint.business;

import ru.feodorkek.dev.crazypoint.dto.DonateInfoDtoOut;
import ru.feodorkek.dev.crazypoint.dto.SocialLinksDtoOut;

public interface CrazyPointInfoUseCases {

    DonateInfoDtoOut getDonateInfo();

    SocialLinksDtoOut getSocialLinks();

}
