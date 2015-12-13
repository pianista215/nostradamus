package com.devsmobile.nostradamus.collector.service;

import com.devsmobile.nostradamus.collector.error.AlreadyInstalledSchemaException;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;


public interface InstallService {

	/**
	 * Test the database with the configuration provided
	 * @throws CollectorPersistenceException
	 */
	public void testDB() throws CollectorPersistenceException;
	
	/**
	 * Test the connection to the database with the current configuration
	 * @throws CollectorPersistenceException
	 */
	public void testDBConnection() throws CollectorPersistenceException;
	
	/**
	 * Install the schemas needed to run the collector
	 * @throws CollectorPersistenceException
	 */
	public void installCollectorSchemas() throws CollectorPersistenceException, AlreadyInstalledSchemaException;
	
}
