package com.spss.ibm.spark.common;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Config {
	private static Properties props = new Properties();
	
	public static void load() {
		File file = new File("../common/src/main/resources/configuration/config.properties");
		if (file.exists()) {
			try {
				props.load(new FileInputStream(file));
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
	
	public static String getProperty(String key) {
		return props.getProperty(key);
	}
}
