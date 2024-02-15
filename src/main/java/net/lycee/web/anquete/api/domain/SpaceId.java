package net.lycee.web.anquete.api.domain;

import org.springframework.web.bind.WebDataBinder;

public record SpaceId(
        String value
) {

    public static void register(WebDataBinder binder) {
        binder.registerCustomEditor(SpaceId.class, new SpaceIdEditor());
    }

}
