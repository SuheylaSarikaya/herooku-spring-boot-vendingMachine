package com.example.vMachine;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class VMachineApplication {

	public static void main(String[] args) {
		SpringApplication.run(VMachineApplication.class, args);
	}

}
