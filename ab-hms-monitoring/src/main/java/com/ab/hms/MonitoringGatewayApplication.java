package com.ab.hms;

import de.codecentric.boot.admin.server.config.EnableAdminServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableAdminServer
public class MonitoringGatewayApplication {

	public static void main(String[] args) {
		SpringApplication.run(MonitoringGatewayApplication.class, args);
	}
}
