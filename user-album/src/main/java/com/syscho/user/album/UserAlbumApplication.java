package com.syscho.user.album;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class UserAlbumApplication {

	public static void main(String[] args) {
		SpringApplication.run(UserAlbumApplication.class, args);
	}

}
