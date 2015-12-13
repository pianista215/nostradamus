package com.devsmobile.nostradamus.collector.persistence;

public interface InstallDAO {

	/**
	 * Get current version of Collector on database
	 * @return
	 */
	public String getVersion();
}
