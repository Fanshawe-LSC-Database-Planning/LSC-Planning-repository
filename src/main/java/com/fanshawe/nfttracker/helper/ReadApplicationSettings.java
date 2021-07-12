package com.fanshawe.nfttracker.helper;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.springframework.stereotype.Service;

@Service
public class ReadApplicationSettings {

	// private static Properties prop = new Properties();

	private static PropertiesConfiguration prop;

	public static Boolean loadPropertiesFile() {
		try {
			prop = new PropertiesConfiguration(ApplicationConstants.APPLICATON_SETTINGS_FILE_PATH);
			return Boolean.TRUE;
		} catch (ConfigurationException ex) {
			return Boolean.FALSE;
		}
	}

	public static Boolean savePropertiesFile() {
		try {
			prop.save();
			return Boolean.TRUE;
		} catch (ConfigurationException ex) {
			return Boolean.FALSE;
		}
	}

	public static String getApplicationProperty(String propertyName) {
		return prop.getProperty(propertyName).toString();
	}

	public static void setApplicationProperty(String propertyName, String propertyValue) {
		prop.setProperty(propertyName, propertyValue);
	}

}