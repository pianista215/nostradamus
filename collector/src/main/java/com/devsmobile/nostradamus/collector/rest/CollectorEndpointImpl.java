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
import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.error.AlreadyInstalledSchemaException;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
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
	

	@RequestMapping(value="/status", method = RequestMethod.GET)
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
				//TODO: Pending on How to change dinamically connections on Spring+Mybatis
				installService.testDB();
				res.setStatus(ResponseStatus.OK);
				res.setMsg("DB successfully configured, please restart the server in order to see the changes.");
			} catch (IOException e){
				res.setStatus(ResponseStatus.NOOK);
				res.setMsg("Impossible to configure database. Check permissions on the file system.");
			} catch(CollectorPersistenceException e){
				res.setStatus(ResponseStatus.NOOK);
				//TODO: Pending on How to change dinamically connections on Spring+Mybatis
				res.setMsg("Cannot access the database with the parameters you passed. Please correct them and try again.");
			}
			
		}
		return res;
	}
	
	@RequestMapping(value="/installSchemas", method = RequestMethod.POST)
	@Override
	public Response installSchemas() {
		LOG.debug("Trying to install the database schemas");
		Response res = new Response();
		try{
			installService.installCollectorSchemas();
			res.setStatus(ResponseStatus.OK);
			res.setMsg("Schemas installed correctly");
		} catch (AlreadyInstalledSchemaException e){
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg(e.getMessage());
		} catch(CollectorPersistenceException e){
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg(e.getMessage());
		}
			
		return res;
	}

	@RequestMapping(value="/createCollection", method = RequestMethod.POST)
	@Override
	public Response createCollection(@RequestBody Collection collection) {
		LOG.debug("Trying to create collection: {}", collection);
		return new Response();
	}

}
