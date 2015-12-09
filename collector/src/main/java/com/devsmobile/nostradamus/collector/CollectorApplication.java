package com.devsmobile.nostradamus.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class CollectorApplication {
	
	
    public static void main(String[] args) {
        SpringApplication.run(CollectorApplication.class, args);
        launchBrowser();
    }
    
    /**
     * Launch browser to configure collector
     */
    private static void launchBrowser(){
    	//TODO:
    }
}
