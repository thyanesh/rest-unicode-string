package org.thyanesh.paypal.assessment.utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.config.PropertyPlaceholderConfigurer;
import org.springframework.core.io.support.PropertiesLoaderUtils;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;

@Component
public class CommonUtils {
	
	public static String getPropertyForKey(final String key) {
		String value = StringUtils.EMPTY;
		try {
			Properties properties = PropertiesLoaderUtils.loadAllProperties("application.properties");
			PropertyPlaceholderConfigurer props2 = new PropertyPlaceholderConfigurer();
			props2.setProperties(properties);
			value = properties.getProperty(key, null);
	//		System.out.println("Key:"+key+" Value:"+value);
		} catch(Exception e) {
			e.getStackTrace();
		}
		return value;
	}

	/**
	 * Converts Json to Java Objects
	 * 
	 * @param jsonObj
	 * @param classType
	 * @return <T>Object
	 */
	public <T> Object convertJsonToJavaObjects(Object jsonObj, Class<T> classType) {
		Object object = null;
		try {
			object = new ObjectMapper().readValue((String) jsonObj, classType);
		} 
		catch (Exception e) {
			e.getMessage();
		}
		return object;
	}
	
	/**
	 * Parse Java Object to json
	 * 
	 * @param dataDTO
	 * @return
	 */
	public static String convertJavaObjectToJsonString(Object dataDTO) {
		
		String jsonString = null;
		try {
			jsonString =  new ObjectMapper().writeValueAsString(dataDTO);
		} catch (Exception e) {
			e.getMessage();
		}
		return jsonString;
	}

	public static void saveProps(Map<Integer, String> stringList) throws Exception{
		Properties properties = new Properties();
		for (Map.Entry<Integer,String> entry : stringList.entrySet()) {
		    properties.put(entry.getKey().toString(), entry.getValue());
		}
		properties.store(new FileOutputStream("data.properties",true), null);
	}
	
	public static Map<Integer, String> loadProps()throws Exception{
		Map<Integer, String> stringList = new HashMap<Integer, String>();
		Properties properties = new Properties();
		properties.load(new FileInputStream("data.properties"));
		for (String key : properties.stringPropertyNames()) {
			stringList.put(Integer.parseInt(key), properties.get(key).toString());
		}
		return stringList;
	}
	
}
