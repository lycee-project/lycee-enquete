package net.lycee.web.enquete.api.controller.space;

import java.util.List;

public record SpaceGetListResponse(
    List<SpaceInfo> spaces
    ) {
}
