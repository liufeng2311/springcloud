package com.beiming;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;
import org.springframework.web.bind.annotation.RestController;


@SpringBootApplication
//@EnableEurekaClient  //不需要注册到服务中心
@EnableConfigServer
@RestController
public class SpringEurekaConfigServerApplication {

	public static void main(String[] args) {
		
		SpringApplication.run(SpringEurekaConfigServerApplication.class, args);
	}
}
