package com.example.getway.config;

import com.netflix.discovery.DiscoveryClient;
import org.springframework.cloud.gateway.config.GatewayProperties;
import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfig {

    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // Route statique pour le service Activite
                .route("rActivite", r -> r.path("/activite/**")
                        .uri("http://localhost:9090/"))

                // Route statique pour le service Session utilisant Discovery Service (Eureka)
                .route("rSession", r -> r.path("/session/**")
                        .uri("lb://MS-SESSION"))


                .build();


    }

    @Bean
    public GatewayProperties gatewayProperties() {
        return new GatewayProperties();
    }
}