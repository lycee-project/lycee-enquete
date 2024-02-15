package net.lycee.web.anquete.api.domain;

import org.springframework.web.bind.WebDataBinder;

public record UserId(
        String value
) {
    public static void register(WebDataBinder binder) {
        binder.registerCustomEditor(UserId.class, new UserIdEditor());
    }

}
