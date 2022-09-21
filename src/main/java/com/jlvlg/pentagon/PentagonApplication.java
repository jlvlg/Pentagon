package com.jlvlg.pentagon;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PentagonApplication {

	public static void main(String[] args) {
		SpringApplication.run(PentagonApplication.class, args);
	}
	@GetMapping
	public String index() {
		return "hello world";
	}
}
