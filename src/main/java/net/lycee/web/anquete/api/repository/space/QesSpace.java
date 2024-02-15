package net.lycee.web.anquete.api.repository.space;

public record QesSpace(
        String spaceId,
        String ownerId,
        String name,
        Long openedTime,
        Long closeTime
) {
}
