package com.devsmobile.nostradamus.collector.service;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;

public interface CollectionService {

	/**
	 * Create a new collection into the database
	 * @param c
	 * @throws CollectorPersistenceException
	 */
	public void createCollection(Collection c) throws CollectorPersistenceException;	
}
