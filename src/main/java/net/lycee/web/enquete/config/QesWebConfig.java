package net.lycee.web.enquete.config;

import net.lycee.web.enquete.interceptor.LyceeLogInterceptor;
import net.lycee.web.enquete.interceptor.LyceeTimeInterceptor;
import net.lycee.web.enquete.interceptor.LyceeAuthzInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class QesWebConfig implements WebMvcConfigurer {

    @Autowired
    private LyceeAuthzInterceptor qesAuthzInterceptor;

    @Autowired
    private LyceeTimeInterceptor lyceeTimeInterceptor;

    @Autowired
    private LyceeLogInterceptor lyceeLogInterceptor;


    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(lyceeLogInterceptor)
                .addPathPatterns("/api/**");
        registry.addInterceptor(lyceeTimeInterceptor)
                .addPathPatterns("/api/**");
        registry.addInterceptor(qesAuthzInterceptor)
                .addPathPatterns("/api/**");
    }
}
