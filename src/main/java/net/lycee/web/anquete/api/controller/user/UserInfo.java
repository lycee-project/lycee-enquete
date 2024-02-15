package net.lycee.web.anquete.api.controller.user;

import net.lycee.web.anquete.api.domain.UserId;

public record UserInfo(
        UserId userId
) {
    public String getUserId() {
        return userId.value();
    }
}
