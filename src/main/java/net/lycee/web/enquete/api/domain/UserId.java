package net.lycee.web.enquete.api.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.web.bind.WebDataBinder;

public record UserId(
        @JsonValue
        String value
) {
    public static void register(WebDataBinder binder) {
        binder.registerCustomEditor(UserId.class, new UserIdEditor());
    }

}
