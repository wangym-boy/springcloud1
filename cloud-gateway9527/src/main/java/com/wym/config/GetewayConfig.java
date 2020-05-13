package com.wym.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GetewayConfig {

    @Bean
    public RouteLocator routes(RouteLocatorBuilder builder){
        RouteLocatorBuilder.Builder routes = builder.routes();
        RouteLocator payment_routh3 = routes.route("payment_routh3", r -> r.path("/guonei").uri("http://news.baidu.com/guonei")).build();
        return payment_routh3;
    }

}
