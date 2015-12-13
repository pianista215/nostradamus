package com.devsmobile.nostradamus.collector;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.boot.autoconfigure.jackson.JacksonAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.json.GsonHttpMessageConverter;

import com.google.gson.Gson;

@SpringBootApplication
@EnableAutoConfiguration(exclude = { JacksonAutoConfiguration.class })
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
    
    /**
     * Gson de/serializer as default
     * @author Pianista
     *
     */
    @Configuration
    @ConditionalOnClass(Gson.class)
    @ConditionalOnMissingClass(name = "com.fasterxml.jackson.core.JsonGenerator")
    @ConditionalOnBean(Gson.class)
    protected static class GsonHttpMessageConverterConfiguration {

        @Bean
        @ConditionalOnMissingBean
        public GsonHttpMessageConverter gsonHttpMessageConverter(Gson gson) {
            GsonHttpMessageConverter converter = new GsonHttpMessageConverter();
            converter.setGson(gson);
            return converter;
        }

    }
}
