package net.lycee.web.anquete.api.domain;

import org.springframework.web.bind.WebDataBinder;

public record AnswerId(
        String value
) {
    public static void register(WebDataBinder binder) {
        binder.registerCustomEditor(AnswerId.class, new AnswerIdEditor());
    }

}
