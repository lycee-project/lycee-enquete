package net.lycee.web.enquete.interceptor;


import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public record LyceeApiKeyInterceptor(
        String key,
        String value
) implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (!(handler instanceof HandlerMethod)) {
            return true;
        }

        String headerValue = request.getHeader(key);
        if (!value.equals(headerValue)) {
            throw new ResponseStatusException(HttpStatus.FORBIDDEN);
        }
        return true;
    }

}
