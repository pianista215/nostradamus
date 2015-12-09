package com.devsmobile.nostradamus.collector.utils;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

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
	
	public PropertiesUtils(){
		InputStream input = null;

		try {

			input = new FileInputStream(COLLECTOR_PROPERTIES);
			prop.load(input);

		} catch(FileNotFoundException e){
			//Create properties file
			LOG.info(COLLECTOR_PROPERTIES + " doesn't exist, we must create it.");
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
	
}
