package net.lycee.web.enquete.api.controller.space;

import net.lycee.web.enquete.api.domain.SpaceId;
import net.lycee.web.enquete.api.domain.UserId;

public record SpaceGetOneResponse(
        SpaceId id,
        UserId ownerId,
        String name,
        Long openedTime,
        Long closeTime
) {
}
