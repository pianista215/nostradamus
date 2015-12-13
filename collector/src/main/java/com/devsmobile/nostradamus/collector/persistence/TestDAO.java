package com.devsmobile.nostradamus.collector.persistence;

import com.devsmobile.nostradamus.collector.error.CollectorPersistenceException;

/**
 * Test DB DAO
 * @author Pianista
 *
 */
public interface TestDAO {

	/**
	 * Test the database connection
	 * @return true if OK, false if NOOK
	 */
	public void testConnection() throws CollectorPersistenceException;
	
	/**
	 * Test the creation of tables
	 * @param tableId TableId random to test
	 * @return true if OK, false if NOOK
	 */
	public void testCreation(long tableId) throws CollectorPersistenceException;
	
	/**
	 * Test the insert of rows into a table
	 * @param tableId TableId random to test
	 * @return true if OK, false if NOOK
	 */
	public void testInsert(long tableId) throws CollectorPersistenceException;
	
	
	/**
	 * Test the deletion of tables
	 * @param tableId TableId random to test
	 * @return true if OK, false if NOOK
	 */
	public void testDrop(long tableId) throws CollectorPersistenceException;
}
