package com.tedu.sp09feign;

import org.springframework.boot.SpringApplication;
import org.springframework.cloud.client.SpringCloudApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

//@EnableDiscoveryClient //找注册中心
@EnableFeignClients  		//扫描声明的接口
//@SpringBootApplication
//@EnableCircuitBreaker
@SpringCloudApplication
public class Sp09FeignApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp09FeignApplication.class, args);
	}

}
