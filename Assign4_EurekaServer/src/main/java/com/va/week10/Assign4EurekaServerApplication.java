package com.va.week10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class Assign4EurekaServerApplication {

	public static void main(String[] args) {
		SpringApplication.run(Assign4EurekaServerApplication.class, args);
	}

}
