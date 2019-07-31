package com.beiming.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RestController;

@Component
@RestController
public class GatewayConfig {

	//定义自己的路由规则
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
	        return builder.routes()
	            .route(p -> p
	                .path("/client/sayHello")
	                .filters(f -> f.addRequestHeader("Hello", "World"))
	                .uri("localhost:9003"))
	            .build();
	}
	
/*	@Bean
	public ServerCodecConfigurer serverCodecConfigurer() {
		DefaultServerCodecConfigurer config = new DefaultServerCodecConfigurer();
		return config;
		
	}*/
}
