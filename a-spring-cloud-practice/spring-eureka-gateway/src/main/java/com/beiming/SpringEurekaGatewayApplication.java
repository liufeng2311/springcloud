package com.beiming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import reactor.core.publisher.Mono;

/**
 *gateway不能引用web依赖，否则会导致路由转发失败 
 *
 */
@SpringBootApplication
@EnableEurekaClient
public class SpringEurekaGatewayApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringEurekaGatewayApplication.class, args);
	}
	
	
}
