package com.devsmobile.nostradamus.collector.rest;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.rest.vo.Response;

public interface CollectorEndpoint {

	/**
	 * Check if all is working correctly and configuration is set
	 * @return
	 */
	public Response status();
	
	
	/**
	 * Create a new collection 
	 * @param collection
	 * @return
	 */
	public Response createCollection(Collection collection);
}
