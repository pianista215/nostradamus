package com.devsmobile.nostradamus.core;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CoreApplication {
	
	@RequestMapping("/")
    public String index() {
        return "Core working";
    }

    public static void main(String[] args) {
        SpringApplication.run(CoreApplication.class, args);
    }
}
