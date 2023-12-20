package com.example.budgets;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class BudgetsApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetsApplication.class, args);
	}

	@GetMapping("/helloWorld")
	public String helloWorld() {
		return "Hello World!";
	}

}
