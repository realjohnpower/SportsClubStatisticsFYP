package com.example.sportsclubstatisticsfyp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.security.servlet.SecurityAutoConfiguration;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication(exclude = {SecurityAutoConfiguration.class})
@EnableWebSecurity
public class SportsClubStatisticsFypApplication {

	public static void main(String[] args) {
		SpringApplication.run(SportsClubStatisticsFypApplication.class, args);
	}

}
