package net.lycee.web.anquete.config;

import net.lycee.web.anquete.interceptor.LyceeLogInterceptor;
import net.lycee.web.anquete.interceptor.LyceeTimeInterceptor;
import net.lycee.web.anquete.interceptor.LyceeAuthzInterceptor;
import net.lycee.web.anquete.api.service.user.TokenService;
import net.lycee.web.anquete.api.service.user.UserService;
import net.lycee.web.anquete.utils.date.LyceeDate;
import net.lycee.web.anquete.utils.date.LyceeDateFactory;
import net.lycee.web.anquete.interceptor.RequestUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

@Configuration
public class BeanConfig {

    @Value("${qes.auth.header-key}")
    private String authHeaderKey;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Bean
    public LyceeLogInterceptor lyceeLogInterceptor() {
        return new LyceeLogInterceptor(authHeaderKey);
    }

    @Bean
    public LyceeAuthzInterceptor lyceeAuthzInterceptor() {
        return new LyceeAuthzInterceptor(authHeaderKey, requestUser(), userService, tokenService);
    }

    @Bean
    @Scope(
            value = WebApplicationContext.SCOPE_REQUEST,
            proxyMode = ScopedProxyMode.TARGET_CLASS)
    public RequestUser requestUser() {
        return new RequestUser();
    }


    @Bean
    public LyceeTimeInterceptor lyceeTimeInterceptor() {
        return new LyceeTimeInterceptor(lyceeDateFactory());
    }

    @Bean
    public LyceeDateFactory lyceeDateFactory() {
        return new LyceeDateFactory();
    }

    @Bean
    public LyceeDate lyceeDate() {
        return new LyceeDate();
    }

}
