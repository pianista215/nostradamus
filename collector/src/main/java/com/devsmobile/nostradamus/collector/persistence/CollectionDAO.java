package com.devsmobile.nostradamus.collector.persistence;

import com.devsmobile.nostradamus.collector.domain.Collection;
import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;

public interface CollectionDAO {

	/**
	 * Create a new collection into the database
	 * @param collection
	 * @throws CollectorPersistenceException
	 */
	public void createCollection(Collection collection) throws CollectorPersistenceException;
}
