package com.spss.ibm.spark.core;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.Set;

import org.apache.commons.io.IOUtils;
import org.codehaus.jackson.map.ObjectMapper;

import com.spss.ibm.spark.common.Config;

public class DataPersist {
	private static final String FILE_SUFFIX_JSON = ".json";
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
	
	public synchronized static void saveAsJson() {
		Properties p2 = new Properties();
		String dataPath = Config.getDataFilePath();
		InputStream in = null;
		try{
			in = new FileInputStream(dataPath);
			p2.load(in);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			IOUtils.closeQuietly(in);
		}
		
		String timestamp = ""+System.currentTimeMillis();
		List<TopicVO> topics = new ArrayList<TopicVO>();
		initTopic(topics,p2);
		OutputVO ovo = new OutputVO();
		ovo.setTimestamp(timestamp);
		ovo.setTopics(topics);
		
		
		String json = toJson(ovo);
		
		saveAsFile(json,timestamp);
		
		String tempfile = "lock.latest";
		saveAsFile(json,tempfile);
		
		String filename = "latest";
		rename(tempfile,filename);
		
		System.out.println(json);
	}
	
	private static void rename(String tempfile, String filename) {
		File f0 = new File(Config.getOutputPath(),tempfile+FILE_SUFFIX_JSON);
		if(f0.exists()){
			f0.renameTo(new File(Config.getOutputPath(),filename+FILE_SUFFIX_JSON));
		}
	}

	private static void saveAsFile(String json, String filename) {
		File f = new File(Config.getOutputPath(),filename+FILE_SUFFIX_JSON);
		if(!f.exists()){
			FileWriter fw = null;
			try {
				f.createNewFile();
				fw = new FileWriter(f);
				fw.write(json);
				fw.flush();
			} catch (IOException e) {
				e.printStackTrace();
			} finally{
				IOUtils.closeQuietly(fw);
			}
		}
	}

	private static String toJson(OutputVO ovo) {
		ObjectMapper map = new ObjectMapper();
		String json = null;
		try {
			json = map.writeValueAsString(ovo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return json;
	}

	private static void initTopic(List<TopicVO> topics,Properties p2) {
		Set<Entry<Object, Object>> entries = p2.entrySet();
		Iterator<Entry<Object, Object>> it = entries.iterator();
		while(it.hasNext()){
			Entry<Object, Object> entry = it.next();
			TopicVO vo = new TopicVO();
			vo.setTopic((String)entry.getKey());
			vo.setHot(Long.parseLong((String)entry.getValue()));
			
			topics.add(vo);
		}
	}

	static class OutputVO{
		private String timestamp;
		private List<TopicVO> topics;
		public String getTimestamp() {
			return timestamp;
		}
		public void setTimestamp(String timestamp) {
			this.timestamp = timestamp;
		}
		public List<TopicVO> getTopics() {
			return topics;
		}
		public void setTopics(List<TopicVO> topics) {
			this.topics = topics;
		}
	}
	
	static class TopicVO{
		private String topic;
		private long hot;
		public String getTopic() {
			return topic;
		}
		public void setTopic(String topic) {
			this.topic = topic;
		}
		public long getHot() {
			return hot;
		}
		public void setHot(long hot) {
			this.hot = hot;
		}
	}
}
