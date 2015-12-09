package com.devsmobile.nostradamus.collector.rest;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.devsmobile.nostradamus.collector.domain.Prueba;
import com.devsmobile.nostradamus.collector.service.InstallService;
import com.devsmobile.nostradamus.collector.utils.PropertiesUtils;

@RestController
@ComponentScan("com.devsmobile.nostradamus.collector.service, com.devsmobile.nostradamus.collector.utils")
public class CollectorEndpointImpl implements CollectorEndpoint{
	
	private static final Logger LOG = LoggerFactory.getLogger(CollectorEndpointImpl.class);
	
	@Autowired
	private InstallService installService;
	
	@Autowired
	private PropertiesUtils propUtils;
	
	@RequestMapping("/")
    public String index() {
		LOG.debug("Call to index");
		String str = "";
		List<Prueba> result = installService.test();
		if(result!=null && !result.isEmpty()){
			for(Prueba p: result){
				str += p.toString();
			}
		} else{
			str = "empty";
		}
		LOG.debug("Index result:{}",str);
        return str;
    }

}
