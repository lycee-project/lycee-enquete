package net.lycee.web.enquete.interceptor;

import lombok.Data;
import net.lycee.web.enquete.api.domain.UserId;

@Data
public class RequestUser {
    private UserId userId = null;
}
