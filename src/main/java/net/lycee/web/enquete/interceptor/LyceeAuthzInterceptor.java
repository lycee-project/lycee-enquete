package net.lycee.web.enquete.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import net.lycee.web.enquete.api.service.user.TokenService;
import net.lycee.web.enquete.api.service.user.UserService;
import net.lycee.web.enquete.api.domain.UserId;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

import java.lang.reflect.Method;

@Slf4j
public record LyceeAuthzInterceptor(
        String key,
        RequestUser user,
        UserService userService,
        TokenService tokenService
) implements HandlerInterceptor {


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        Method method = ((HandlerMethod) handler).getMethod();
        if (AnnotationUtils.findAnnotation(method, LyceeAnonymous.class) != null) {
            return true;
        }

        if (AnnotationUtils.findAnnotation(method.getDeclaringClass(), LyceeAuthorized.class) != null ||
                AnnotationUtils.findAnnotation(method, LyceeAuthorized.class) != null) {
            if (!authorize(request)) {
                throw new ResponseStatusException(HttpStatus.FORBIDDEN);
            }
        }

        return true;
    }

    private boolean authorize(HttpServletRequest request) {
        String headerValue = request.getHeader(this.key);

        if (headerValue == null || headerValue.isEmpty()) {
            return false;
        }

        UserId userId = tokenService.convertToId(headerValue);

        boolean exists = userService.existsUserId(userId);
        if (exists) {
            user.setUserId(userId);
        }
        return exists;
    }
}
