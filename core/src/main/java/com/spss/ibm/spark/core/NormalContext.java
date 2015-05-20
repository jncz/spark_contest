package com.spss.ibm.spark.core;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.PairFunction;

import scala.Tuple2;

public class NormalContext {
	private static final String path = "/home/liping/Documents/dev/spark_contest/common/src/main/resources/Sample/raw/";
	
	private static void run(JavaSparkContext sc){
		JavaRDD<String> rdd = sc.textFile(path).cache();
		
		@SuppressWarnings("serial")
		Map<String, Object> num = rdd.mapToPair(new PairFunction<String,String,Integer>(){

			public Tuple2<String, Integer> call(String t) throws Exception {
				String[] vs = t.split(",");
				Tuple2<String, Integer> tu = new Tuple2<String, Integer>(vs[0],1);
				
				return tu;
			}}).countByKey();
		
		
		Set<Entry<String, Object>> entires = num.entrySet();
		Iterator<Entry<String, Object>> it = entires.iterator();
		while(it.hasNext()){
			Entry<String, Object> entry = it.next();
			
			DataPersist.persist(entry.getKey(),(Long)entry.getValue());
		}
	}
	
	public static void main(String[] args) throws InterruptedException{
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("hot topic");
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		try{
			for(;;){
				run(sc);
				Debuger.print();
				Thread.sleep(5000);
			}
		}finally{
			sc.close();
		}
	}
}
