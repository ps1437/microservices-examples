package com.syscho.auth;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.oauth2.config.annotation.web.configuration.EnableAuthorizationServer;

import com.syscho.auth.config.entity.UserDO;
import com.syscho.auth.config.repository.UserRepository;

@SpringBootApplication
@EnableAuthorizationServer
public class AuthServerJwtApplication implements CommandLineRunner {

	@Autowired
	UserRepository userRepository;

	@Autowired
	PasswordEncoder encoder;

	public static void main(String[] args) {
		SpringApplication.run(AuthServerJwtApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println(encoder.encode("user1"));
		;
		System.out.println(encoder.encode("admin1"));
		;

		userRepository.save(new UserDO(1, "admin1", encoder.encode("admin"), "admin@gmail.com", true, true, true, true));
		userRepository.save(new UserDO(2, "user1", encoder.encode("user"), "user@gmail.com", true, true, true, true));

	}

}
