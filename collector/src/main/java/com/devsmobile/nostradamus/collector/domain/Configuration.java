package com.devsmobile.nostradamus.collector.domain;

/**
 * Configuration for database access
 * @author Pianista
 *
 */
public class Configuration {

	private String jdbcUrl;
	private String user;
	private String password;
	
	public String getJdbcUrl() {
		return jdbcUrl;
	}
	public void setJdbcUrl(String jdbcUrl) {
		this.jdbcUrl = jdbcUrl;
	}
	public String getUser() {
		return user;
	}
	public void setUser(String user) {
		this.user = user;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
}
