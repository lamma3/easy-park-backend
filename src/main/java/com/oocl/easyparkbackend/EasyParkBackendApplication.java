package com.oocl.easyparkbackend;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class EasyParkBackendApplication {

	public static void main(String[] args) {
		SpringApplication.run(EasyParkBackendApplication.class, args);
	}

	@Value("${env}")
	private String env;

	@PostConstruct
	public void test() {
		System.out.println(env);
	}
}
