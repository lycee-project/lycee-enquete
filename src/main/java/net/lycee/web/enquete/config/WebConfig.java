package net.lycee.web.enquete.config;

import net.lycee.web.enquete.interceptor.LyceeApiKeyInterceptor;
import net.lycee.web.enquete.interceptor.LyceeAuthzInterceptor;
import net.lycee.web.enquete.interceptor.LyceeLogInterceptor;
import net.lycee.web.enquete.interceptor.LyceeTimeInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Autowired
    private LyceeApiKeyInterceptor lyceeApiKeyInterceptor;

    @Autowired
    private LyceeAuthzInterceptor lyceeAuthzInterceptor;

    @Autowired
    private LyceeTimeInterceptor lyceeTimeInterceptor;

    @Autowired
    private LyceeLogInterceptor lyceeLogInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(lyceeApiKeyInterceptor)
                .addPathPatterns("/api/**");
        registry.addInterceptor(lyceeLogInterceptor)
                .addPathPatterns("/api/**");
        registry.addInterceptor(lyceeTimeInterceptor)
                .addPathPatterns("/api/**");
        registry.addInterceptor(lyceeAuthzInterceptor)
                .addPathPatterns("/api/**");
    }

    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/**")
                .allowedMethods("*")
                .allowedHeaders("*");
    }
}
