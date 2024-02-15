package net.lycee.web.enquete.api.controller.user;

import net.lycee.web.enquete.api.domain.UserId;

public record UserInfo(
        UserId userId
) {
    public String getUserId() {
        return userId.value();
    }
}
