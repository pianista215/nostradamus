package com.devsmobile.nostradamus.collector;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsmobile.nostradamus.collector.domain.Prueba;
import com.devsmobile.nostradamus.collector.persistence.mappers.PruebaMapper;

@SpringBootApplication
@RestController
@MapperScan("com.devsmobile.nostradamus.collector.persistence.mappers")
public class CollectorApplication {
	
	@Autowired
	private PruebaMapper pruebaMapper;
	
	@RequestMapping("/")
    public String index() {
		String str = "";
		List<Prueba> result = pruebaMapper.findAll();
		if(result!=null && !result.isEmpty()){
			for(Prueba p: result){
				str += p.toString();
			}
		} else{
			str = "empty";
		}
        return str;
    }

    public static void main(String[] args) {
        SpringApplication.run(CollectorApplication.class, args);
    }
}
