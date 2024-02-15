package net.lycee.web.enquete.api.service.space;

import net.lycee.web.enquete.api.domain.UserId;

public record SpaceRegisterDto(
        UserId userId,
        String name,
        Long closeTime
) {
}
