package com.tedu.sp08dashboard;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;

@EnableHystrixDashboard
@EnableDiscoveryClient
@SpringBootApplication
@EnableHystrix
public class Sp08DashboardApplication {

	public static void main(String[] args) {
		SpringApplication.run(Sp08DashboardApplication.class, args);
	}

}
