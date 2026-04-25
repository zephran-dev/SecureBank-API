package com.securebank.api;

import org.springframework.boot.SpringApplication;

public class TestSecureBankApiApplication {

	public static void main(String[] args) {
		SpringApplication.from(SecureBankApiApplication::main).with(TestcontainersConfiguration.class).run(args);
	}

}
