package com.beiming;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

@FeignClient(value = "spring-eureka-client",fallback = FeginServiceHiHystric.class)
public interface FeginService {

	@PostMapping("client/sayHello")   //此处为全路径
	public String sayHello();
}
