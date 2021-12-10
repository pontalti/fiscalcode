package com;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@ComponentScan("com")
@EnableTransactionManagement
@EnableJpaRepositories(basePackages = "com")
public class FiscalCodeApplication {

	public FiscalCodeApplication() {
		super();
	}
	
	public static void main(String[] args) {
		SpringApplication.run(FiscalCodeApplication.class, args);
	}

}
