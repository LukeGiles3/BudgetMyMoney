package com.example.budgets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication

public class BudgetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetsApplication.class, args);
	}
}
