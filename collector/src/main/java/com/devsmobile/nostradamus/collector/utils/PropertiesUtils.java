package com.devsmobile.nostradamus.collector.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

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
			
			if(!isFilledCorrectly()){
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
	 * Check if property is loaded
	 * @return
	 */
	public boolean isLoaded(){
		return !prop.isEmpty();
	}
	
	/**
	 * Create the file in order to help in the manual installation to the user
	 */
	private void createEmptyProperties(){
		FileOutputStream output = null;
		try {
			output = new FileOutputStream(COLLECTOR_PROPERTIES);
			prop.put(JDBC_KEY, "");
			prop.put(USER_KEY, "");
			prop.put(PASSWORD_KEY, "");
			prop.store(output, "Please fill this file with the property information of your environment");
			output.close();
		} catch (IOException e){
			LOG.error("Impossible to create empty "+COLLECTOR_PROPERTIES,e);
		} finally {
			
		}
		
	}
	
	/**
	 * Check that the essentials properties are set
	 * @return
	 */
	private boolean isFilledCorrectly(){
		return !StringUtils.isEmpty(prop.get(USER_KEY))
			&& !StringUtils.isEmpty(prop.getProperty(PASSWORD_KEY))
			&& !StringUtils.isEmpty(prop.getProperty(JDBC_KEY));
	}
	
}
