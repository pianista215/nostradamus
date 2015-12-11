package com.devsmobile.nostradamus.collector.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.devsmobile.nostradamus.collector.domain.Configuration;

/**
 * Manage the properties load/save
 * @author Pianista
 *
 */
@Component
public class PropertiesUtils {
	
	private Properties prop = new Properties();
	private final static String COLLECTOR_PROPERTIES = "collector.properties";
	private static final Logger LOG = LoggerFactory.getLogger(PropertiesUtils.class);
	private static final String JDBC_KEY = "collector.database.jdbcUrl";
	private static final String USER_KEY = "collector.database.user";
	private static final String PASSWORD_KEY = "collector.database.password";
	
	public PropertiesUtils(){
		InputStream input = null;

		try {

			input = new FileInputStream(COLLECTOR_PROPERTIES);
			prop.load(input);
			
			if(!isValidProperties()){
				LOG.error("¡¡¡¡WARNING: collector.database.jdbcUrl, collector.database.user, collector.database.password not FOUND!!!!!");
			}

		} catch(FileNotFoundException e){
			
			//Create properties file
			LOG.info(COLLECTOR_PROPERTIES + " doesn't exist, creating file.");
			createEmptyProperties();
			
		} catch (IOException e) {
			LOG.error("Error accessing file "+ COLLECTOR_PROPERTIES,e);
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					LOG.error("Error closing file "+ COLLECTOR_PROPERTIES,e);
				}
			}
		}
	}
	
	/**
	 * Check that the essentials properties are set
	 * @return
	 */
	public boolean isValidProperties(){
		return !StringUtils.isEmpty(prop.get(USER_KEY))
			&& !StringUtils.isEmpty(prop.getProperty(PASSWORD_KEY))
			&& !StringUtils.isEmpty(prop.getProperty(JDBC_KEY));
	}
	
	/**
	 * Create/Override the collector.properties with the configuration provided
	 * @param config
	 * @throws IOException
	 */
	public void createPropertiesWithConfig(Configuration config) throws IOException{
		try {
			createPropertiesFile(config.getJdbcUrl(), config.getUser(), config.getPassword(), new Date().toString());
		} catch (IOException e){
			LOG.error("Impossible to write config",e);
			throw e;
		}
	}
	
	/**
	 * Create the file in order to help in the manual installation to the user
	 */
	private void createEmptyProperties(){
		try {
			createPropertiesFile("","","","Please fill this file with the property information of your environment");
		} catch (IOException e){
			LOG.error("Impossible to create empty config",e);
		}
	}
	
	private void createPropertiesFile(String jdbc, String user, String password, String comment) throws IOException{
		FileOutputStream output = null;
		output = new FileOutputStream(COLLECTOR_PROPERTIES);
		prop.put(JDBC_KEY, jdbc);
		prop.put(USER_KEY, user);
		prop.put(PASSWORD_KEY, password);
		prop.store(output,comment);
		output.close();	
	}
	
	
	
}
