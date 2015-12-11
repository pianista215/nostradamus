package com.devsmobile.nostradamus.collector.rest;

import com.devsmobile.nostradamus.collector.domain.Configuration;
import com.devsmobile.nostradamus.collector.domain.Schema;

public interface CollectorEndpoint {

	/**
	 * Check if all is working correctly and configuration is set
	 * @return
	 */
	public String status();
	
	/**
	 * Set a configuration if no one is provided
	 * @param config
	 * @return
	 */
	public String configure(Configuration config);
	
	/**
	 * Create a schema 
	 * @param schema
	 * @return
	 */
	public String createSchema(Schema schema);
}
