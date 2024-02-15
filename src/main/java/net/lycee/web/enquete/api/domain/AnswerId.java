package net.lycee.web.enquete.api.domain;

import com.fasterxml.jackson.annotation.JsonValue;
import org.springframework.web.bind.WebDataBinder;

public record AnswerId(
        @JsonValue
        String value
) {
    public static void register(WebDataBinder binder) {
        binder.registerCustomEditor(AnswerId.class, new AnswerIdEditor());
    }

}
