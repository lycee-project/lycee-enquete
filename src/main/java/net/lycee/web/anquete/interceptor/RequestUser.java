package net.lycee.web.anquete.interceptor;

import lombok.Data;
import net.lycee.web.anquete.api.domain.UserId;

@Data
public class RequestUser {
    private UserId userId = null;
}
