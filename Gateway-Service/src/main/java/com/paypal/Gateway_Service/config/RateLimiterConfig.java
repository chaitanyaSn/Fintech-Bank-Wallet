package com.paypal.Gateway_Service.config;


import org.springframework.cloud.gateway.filter.ratelimit.KeyResolver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import reactor.core.publisher.Mono;

@Configuration
public class RateLimiterConfig {
    @Bean
    public KeyResolver ipKeyResolver() {
        return exchange -> Mono.just(
                exchange.getRequest().getRemoteAddress().getAddress().getHostAddress()
        );
    }
//    @Bean
//    public KeyResolver loginRegisterKeyResolver() {
//        return exchange -> {
//            String path = exchange.getRequest().getURI().getPath();
//            if (path.contains("/login") || path.contains("/register")) {
//                return Mono.just(exchange.getRequest().getRemoteAddress().getAddress().getHostAddress());
//            }
//            // For internal routes like /users/info — no rate limiting key (no limit)
//            return Mono.just("internal");
//        };
//    }
}
