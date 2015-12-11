package com.devsmobile.nostradamus.collector.rest;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsmobile.nostradamus.collector.domain.Configuration;
import com.devsmobile.nostradamus.collector.domain.Schema;
import com.devsmobile.nostradamus.collector.rest.vo.Response;
import com.devsmobile.nostradamus.collector.rest.vo.ResponseStatus;
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
	public Response status() {
		LOG.debug("Status invoked");
		Response res = new Response();
		if(!propUtils.isValidProperties()){
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg("Properties file not configured. Please fill collector.properties and restart the server.");
		} else{
			res.setStatus(ResponseStatus.OK);
			res.setMsg("Collector version :{} DB status:{} {} objects inserted");
		}
		return res;
	}

	@RequestMapping(value="/configure", method = RequestMethod.POST)
	@Override
	public Response configure(@RequestBody Configuration config) {
		LOG.debug("Trying to set up new config: {}", config);
		Response res = new Response();
		if(propUtils.isValidProperties()){
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg("Sorry but collector.properties is already configured.");
		} else if(config==null || !config.isValid()){
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg("Invalid configuration provided.");
		} else{
			try{
				propUtils.createPropertiesWithConfig(config);
				res.setStatus(ResponseStatus.OK);
				res.setMsg("DB successfully configured.");
			} catch (IOException e){
				res.setStatus(ResponseStatus.NOOK);
				res.setMsg("Impossible to configure database.");
			}
			
		}
		return res;
	}

	@RequestMapping(value="/createSchema", method = RequestMethod.POST)
	@Override
	public Response createSchema(@RequestBody Schema schema) {
		LOG.debug("Trying to create schema: {}", schema);
		return new Response();
	}

}
