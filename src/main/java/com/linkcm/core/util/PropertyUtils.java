package com.linkcm.core.util;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

import org.slf4j.Logger;
import org.springframework.core.io.DefaultResourceLoader;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.util.DefaultPropertiesPersister;
import org.springframework.util.PropertiesPersister;

public final class PropertyUtils {
	
	public static final String DEFAULT_PROPERTIES = "application.properties";
	
	private static Logger logger = LoggerUtils.getLogger(PropertyUtils.class);
	
	private PropertyUtils() {
		
	}

	public static String get(Object key, String file) {
		Properties props = new Properties();
		PropertiesPersister propertiesPersister = new DefaultPropertiesPersister();
		ResourceLoader resourceLoader = new DefaultResourceLoader();
		Resource resource = resourceLoader.getResource("application.properties");
		InputStream is;
		try {
			is = resource.getInputStream();
			propertiesPersister.load(props, new InputStreamReader(is, "UTF-8"));
		} catch (IOException e) {
			logger.error(e.getMessage(), e);
		}
		Object result = props.get(key);
		return result == null ? "" : result.toString();
	}
	
	public static String get(Object key) {
		return get(key,DEFAULT_PROPERTIES);
	}

}
