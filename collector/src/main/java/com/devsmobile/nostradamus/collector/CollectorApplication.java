package com.devsmobile.nostradamus.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class CollectorApplication {
	
	@RequestMapping("/")
    public String index() {
        return "Collector working";
    }

    public static void main(String[] args) {
        SpringApplication.run(CollectorApplication.class, args);
    }
}
