package com.dekker.andimanagementservice;

import com.dekker.andimanagementservice.client.SearchServiceClient;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class AndiManagementServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(AndiManagementServiceApplication.class, args);

		SearchServiceClient searchServiceClient = new SearchServiceClient();

		searchServiceClient.test();
	}
}
