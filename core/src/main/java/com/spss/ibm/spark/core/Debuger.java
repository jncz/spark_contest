package com.spss.ibm.spark.core;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;

public class Debuger {
	private static final String dataPath = "/home/liping/Downloads/data/data.properties";
	public static void print(){
		Properties p = new Properties();
		InputStream in = null;
		try{
			in = new FileInputStream(dataPath);
			p.load(in);
		} catch (Exception e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(in);
		}
		Set<Entry<Object, Object>> entires = p.entrySet();
		Iterator<Entry<Object, Object>> it = entires.iterator();
		while(it.hasNext()){
			Entry<Object, Object> entry = it.next();
			
			System.out.println(entry.getKey()+"---"+entry.getValue());
		}
	}
}
