package net.lycee.web.anquete.api.domain;

import org.springframework.web.bind.WebDataBinder;

public record QuestionId(
        String value
) {
    public static void register(WebDataBinder binder) {
        binder.registerCustomEditor(QuestionId.class, new QuestionIdEditor());
    }

}
