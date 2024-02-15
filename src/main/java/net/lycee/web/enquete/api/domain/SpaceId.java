package net.lycee.web.enquete.api.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.web.bind.WebDataBinder;

public record SpaceId(
        @JsonValue
        String value
) {

    public static void register(WebDataBinder binder) {
        binder.registerCustomEditor(SpaceId.class, new SpaceIdEditor());
    }

}
