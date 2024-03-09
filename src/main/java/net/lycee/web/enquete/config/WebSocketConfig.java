package net.lycee.web.enquete.config;

import net.lycee.web.enquete.live.LiveWebSocketInterceptor;
import net.lycee.web.enquete.live.QesWebSocketHandler;
import net.lycee.web.enquete.live.QesWebSocketInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.config.MessageBrokerRegistry;
import org.springframework.web.socket.config.annotation.EnableWebSocketMessageBroker;
import org.springframework.web.socket.config.annotation.StompEndpointRegistry;
import org.springframework.web.socket.config.annotation.WebSocketMessageBrokerConfigurer;
import org.springframework.web.socket.config.annotation.WebSocketTransportRegistration;

@Configuration
@EnableWebSocketMessageBroker
public class WebSocketConfig implements WebSocketMessageBrokerConfigurer {

    @Value("${lycee.websocket.endpoint}")
    private String endpoint;

    @Override
    public void configureWebSocketTransport(WebSocketTransportRegistration registry) {
        registry.addDecoratorFactory(delegate ->
                new QesWebSocketHandler(delegate, webSocketInterceptor(), endpoint)
        );
    }

    @Bean
    public QesWebSocketInterceptor webSocketInterceptor() {
        return new LiveWebSocketInterceptor(endpoint);
    }

    @Override
    public void configureMessageBroker(MessageBrokerRegistry registry) {

        registry.enableSimpleBroker("/notice");
        registry.setApplicationDestinationPrefixes("/send");
    }


    @Value("${lycee.websocket.allowed-origin-pattern}")
    private String allowedOriginPattern;

    @Override
    public void registerStompEndpoints(StompEndpointRegistry registry) {
        registry.addEndpoint("/%s/*".formatted(endpoint))
                .setAllowedOriginPatterns(allowedOriginPattern)
                ;
    }
}
