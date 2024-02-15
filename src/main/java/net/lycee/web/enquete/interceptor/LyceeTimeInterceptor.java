package net.lycee.web.enquete.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import net.lycee.web.enquete.utils.date.LyceeDateFactory;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.HandlerInterceptor;

public record LyceeTimeInterceptor(
        LyceeDateFactory dateFactory
) implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        RequestContextHolder
                .currentRequestAttributes()
                .setAttribute("lyceeDate", dateFactory.get(), RequestAttributes.SCOPE_REQUEST);

        return true;
    }
}
