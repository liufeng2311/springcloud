package com.beiming;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@RequestMapping("/ribbon")
public class RibbonController {

	@Autowired
	private RestTemplate restTemplate;
	
    @PostMapping("/sayHello")
    @HystrixCommand(fallbackMethod = "hiError") //定义方法调用失败后的处理方式
    public String hiService() {
        return restTemplate.getForObject("http://spring-eureka-client/client/sayHello",String.class);
    }
    
    public String hiError() {
        return "admin"+ "sorry,error!";
    }
}
