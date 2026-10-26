package com.hirehub.hirehub;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;

@SpringBootApplication
@EnableCaching
public class HirehubApplication {

	public static void main(String[] args) {
		SpringApplication.run(HirehubApplication.class, args);
	}

}
