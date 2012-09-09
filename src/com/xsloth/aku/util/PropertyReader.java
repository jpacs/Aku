package com.xsloth.aku.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyReader {
	
	private Properties properties;
	
	private Properties getProperties(){
		
		InputStream io = PropertyReader.class.getResourceAsStream("/data.properties");
		
		properties = new Properties();
		try {
			properties.load(io);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return properties;
	}
	
	public String getProperty(String key) throws IOException{
		return getProperties().getProperty(key);
	}
}
