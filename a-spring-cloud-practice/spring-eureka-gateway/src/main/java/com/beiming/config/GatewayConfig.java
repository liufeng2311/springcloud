package com.beiming.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 *代码配置路由
 *此处不起作用，未解决 
 *
 */
@Configuration
@RestController
public class GatewayConfig {

	//定义自己的路由规则
	@Bean
	public RouteLocator myRoutes(RouteLocatorBuilder builder) {
		return builder.routes()
				.route("client",p -> p
						.path("/client/**")
						.filters(f -> f.addRequestHeader("Hello", "World"))
						.uri("localhost:9003"))  //该属性只需要规定域名或者ip+port，
				.route("ribbon",p -> p
						.path("/ribbon/**")
						.filters(f -> f
								.hystrix(config -> config
										.setName("hystrix")
										.setFallbackUri("forward:/fallback")))
						.uri("localhost:9003"))
				.build();
	}

	@RequestMapping("/fallback")
	public Mono<String> fallback() {
		return Mono.just("fallback");
	}
}
