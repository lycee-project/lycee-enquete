package net.lycee.web.anquete.api.service.space;

import net.lycee.web.anquete.api.domain.UserId;

public record SpaceRegisterDto(
        UserId userId,
        String name,
        Long closeTime
) {
}
