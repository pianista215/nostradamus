package com.devsmobile.nostradamus.collector.rest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.error.CollectionNotExistsException;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;
import com.devsmobile.nostradamus.collector.error.ObjectNotValidException;
import com.devsmobile.nostradamus.collector.rest.vo.AddObjectRQ;
import com.devsmobile.nostradamus.collector.rest.vo.Response;
import com.devsmobile.nostradamus.collector.rest.vo.ResponseStatus;
import com.devsmobile.nostradamus.collector.service.CollectionService;
import com.devsmobile.nostradamus.collector.service.InstallService;
import com.devsmobile.nostradamus.collector.utils.PropertiesUtils;

@RestController
@ComponentScan("com.devsmobile.nostradamus.collector.service, com.devsmobile.nostradamus.collector.utils")
public class CollectorEndpointImpl implements CollectorEndpoint{
	
	private static final Logger LOG = LoggerFactory.getLogger(CollectorEndpointImpl.class);
	
	@Autowired
	private PropertiesUtils propUtils;
	
	@Autowired
	private InstallService installService;
	
	@Autowired
	private CollectionService collectionService;	
	

	@RequestMapping(value="/status", method = RequestMethod.GET)
	@Override
	public Response status() {
		LOG.debug("Status invoked");
		Response res = new Response();
		if(!propUtils.isValidProperties()){
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg("Properties file not configured. Please fill collector.properties and restart the server.");
		} else{
			try{
				installService.testDBConnection();
				res.setStatus(ResponseStatus.OK);
				res.setMsg("Collector version :{} DB status:{} {} objects inserted");
			} catch(CollectorPersistenceException e){
				res.setStatus(ResponseStatus.NOOK);
				res.setMsg("Impossible to connect to the database");
			}
		}
		return res;
	}

	@RequestMapping(value="/createCollection", method = RequestMethod.POST)
	@Override
	public Response createCollection(@RequestBody Collection collection) {
		LOG.debug("Trying to create collection: {}", collection);
		Response res = new Response();
		//TODO: Validation
		try{
			collectionService.createCollection(collection);
			res.setStatus(ResponseStatus.OK);
			res.setMsg("Collection created correctly");
		} catch(CollectorPersistenceException e){
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg("Impossible to create Collection");
		}
		return res;
	}
	
	@RequestMapping(value="/addObjects", method = RequestMethod.POST)
	@Override
	public Response addObjects(@RequestBody AddObjectRQ addObjectRQ){
		LOG.debug("AddObjects :{}", addObjectRQ);
		Response res = new Response();
		try {
			collectionService.addObjects(addObjectRQ.getCollectionId(), addObjectRQ.getObjects());
			res.setStatus(ResponseStatus.OK);
		} catch (CollectorPersistenceException | CollectionNotExistsException | ObjectNotValidException e) {
			res.setStatus(ResponseStatus.NOOK);
			res.setMsg(e.getMessage());
		}
		return res;
	}

}
