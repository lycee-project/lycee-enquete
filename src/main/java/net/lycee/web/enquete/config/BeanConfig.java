package net.lycee.web.enquete.config;

import am.ik.yavi.factory.ValidatorFactory;
import am.ik.yavi.message.MessageFormatter;
import am.ik.yavi.message.MessageSourceMessageFormatter;
import net.lycee.web.enquete.api.service.user.TokenService;
import net.lycee.web.enquete.api.service.user.UserService;
import net.lycee.web.enquete.interceptor.*;
import net.lycee.web.enquete.utils.date.LyceeDate;
import net.lycee.web.enquete.utils.date.LyceeDateFactory;
import org.apache.logging.log4j.message.Message;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;
import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.web.context.WebApplicationContext;

import java.time.ZoneOffset;

@Configuration
public class BeanConfig {

    @Value("${qes.auth.header-key}")
    private String authHeaderKey;

    @Value("${lycee.api.header-key}")
    private String apiHeaderKey;

    @Value("${lycee.api.header-value}")
    private String apiHeaderValue;

    @Autowired
    private UserService userService;

    @Autowired
    private TokenService tokenService;

    @Bean
    public LyceeApiKeyInterceptor lyceeApiKeyInterceptor() {
        return new LyceeApiKeyInterceptor(apiHeaderKey, apiHeaderValue);
    }

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
        // FIXME: 暫定で日本時間で設定している，プロパティ化したい
        return new LyceeDate(ZoneOffset.ofHours(9));
    }

    @Bean
    public ValidatorFactory yaviValidatorFactory(MessageSource messageSource) {
        MessageFormatter messageFormatter = new MessageSourceMessageFormatter(messageSource::getMessage);
        return new ValidatorFactory(".", messageFormatter);
    }

}
