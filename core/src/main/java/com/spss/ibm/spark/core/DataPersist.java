package com.spss.ibm.spark.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Properties;

import org.apache.commons.io.IOUtils;

import com.spss.ibm.spark.common.Config;

public class DataPersist {
	private static final Properties p = new Properties();
	
	public synchronized static void persist(String key,long v){
		String dataPath = Config.getDataFilePath();
		InputStream in = null;
		try{
			in = new FileInputStream(dataPath);
			p.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(in);
		}
		
		if(p.containsKey(key)){
			long value = Long.parseLong((String) p.get(key));
			value = value + v;
			p.put(key, ""+value);
		}else{
			p.put(key, ""+v);
		}
		
		OutputStream out = null;
		try {
			File dbFile = new File(dataPath);
			if(!dbFile.exists()){
				dbFile.createNewFile();
			}
			out = new FileOutputStream(dbFile);
			p.store(out , "timestamp:"+System.currentTimeMillis());
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} finally{
			IOUtils.closeQuietly(out);
		}
	}
}
