package net.lycee.web.anquete.api.controller.space;

import java.util.List;

public record SpaceGetListResponse(
    List<SpaceInfo> spaces
    ) {
}
