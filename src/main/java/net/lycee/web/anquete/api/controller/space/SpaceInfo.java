package net.lycee.web.anquete.api.controller.space;

import net.lycee.web.anquete.api.domain.SpaceId;
import net.lycee.web.anquete.api.domain.UserId;

public record SpaceInfo (
        SpaceId id,
        UserId ownerId,
        String name,
        Long openedTime,
        Long closeTime
) {
}
