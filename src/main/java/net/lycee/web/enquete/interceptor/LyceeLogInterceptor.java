package net.lycee.web.enquete.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import java.util.UUID;

@Slf4j
public record LyceeLogInterceptor(
        String key
) implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (handler instanceof HandlerMethod) {
            MDC.clear();

            MDC.put("marker", "API");
            MDC.put("requestId", UUID.randomUUID().toString());
            MDC.put("userId", request.getHeader(key));
            MDC.put("method", request.getMethod());
            MDC.put("requestUri", request.getRequestURI());

            // 呼び出しログ
            log.info("");
        }

        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        // 返却ログ
        // log.info();

        MDC.clear();
    }
}
