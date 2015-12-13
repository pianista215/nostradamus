package com.devsmobile.nostradamus.collector.persistence;

import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;

public interface InstallationDAO {

	/**
	 * Get current version of Collector on database
	 * @return
	 * @throws CollectorPersistenceException
	 */
	public String getVersion() throws CollectorPersistenceException;
	
	
	//List of schemas ordered by order of installation
	//TODO: For machine learning data it remains algorithm, configuration, etc...
	
	/**
	 * Install Enviroment Information table
	 * @throws CollectorPersistenceException
	 */
	public void installSchemaEnvironmentInformation() throws CollectorPersistenceException;
	
	/**
	 * Install Collection table
	 * @throws CollectorPersistenceException
	 */
	public void installSchemaCollection() throws CollectorPersistenceException;
	
	/**
	 * Install Parameter Type table
	 * @throws CollectorPersistenceException
	 */
	public void installSchemaParameterType() throws CollectorPersistenceException;
	
	/**
	 * Install Training parameters table
	 * @throws CollectorPersistenceException
	 */
	public void installSchemaTrainingParameters() throws CollectorPersistenceException;
	
}
