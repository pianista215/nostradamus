package com.devsmobile.nostradamus.collector.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsmobile.nostradamus.collector.domain.Configuration;
import com.devsmobile.nostradamus.collector.domain.Schema;
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
	

	@RequestMapping(value="/", method = RequestMethod.GET)
	@Override
	public String status() {
		LOG.debug("Status invoked");
		if(!propUtils.isValidProperties()){
			return "Properties file not configured. Please fill collector.properties and restart the server.";
		} else{
			return "Collector version :{}\n DB status:{}\n {} objects inserted";
		}
	}

	@RequestMapping(value="/configure", method = RequestMethod.GET)
	@Override
	public String configure(Configuration config) {
		LOG.debug("Trying to set up new config: {}", config);
		if(propUtils.isValidProperties()){
			return "Sorry but collector.properties is already configured.";
		} else{
			//Mock
			Configuration c = new Configuration();
			c.setJdbcUrl("MOCK_JDBC");
			c.setUser("MOCK_USER");
			c.setPassword("MOCK_PASSWORD");
			propUtils.createPropertiesWithConfig(c);
			return "DB successfully configured:{}";	
		}
	}

	@RequestMapping(value="/createSchema", method = RequestMethod.POST)
	@Override
	public String createSchema(Schema schema) {
		LOG.debug("Trying to create schema: {}", schema);
		return "{}";
	}

}
