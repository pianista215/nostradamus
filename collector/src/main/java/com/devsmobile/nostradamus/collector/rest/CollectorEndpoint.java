package com.devsmobile.nostradamus.collector.rest;

import com.devsmobile.nostradamus.collector.domain.Configuration;
import com.devsmobile.nostradamus.collector.domain.Schema;
import com.devsmobile.nostradamus.collector.rest.vo.Response;

public interface CollectorEndpoint {

	/**
	 * Check if all is working correctly and configuration is set
	 * @return
	 */
	public Response status();
	
	/**
	 * Set a configuration if no one is provided
	 * @param config
	 * @return
	 */
	public Response configure(Configuration config);
	
	/**
	 * Create a schema 
	 * @param schema
	 * @return
	 */
	public Response createSchema(Schema schema);
}
