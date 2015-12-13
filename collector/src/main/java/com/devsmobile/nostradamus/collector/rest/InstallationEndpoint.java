package com.devsmobile.nostradamus.collector.rest;

import com.devsmobile.nostradamus.collector.domain.Configuration;
import com.devsmobile.nostradamus.collector.rest.vo.Response;

public interface InstallationEndpoint {

	/**
	 * Set a configuration if no one is provided
	 * @param config
	 * @return
	 */
	public Response configure(Configuration config);
	
	/**
	 * Install the schemas into the database
	 * @return
	 */
	public Response installSchemas();
}
