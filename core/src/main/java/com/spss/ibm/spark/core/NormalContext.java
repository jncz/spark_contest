package com.spss.ibm.spark.core;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.JavaPairRDD;
import org.apache.spark.api.java.JavaRDD;
import org.apache.spark.api.java.JavaSparkContext;
import org.apache.spark.api.java.function.Function;

public class NormalContext {
	private static final String path = "/home/liping/Documents/dev/spark_contest/common/src/main/resources/Sample/raw";
	public static void main(String[] args){
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName("hot topic");
		JavaSparkContext sc = new JavaSparkContext(conf);
		
		JavaRDD<String> rdd = sc.textFile(path);
		
		rdd.map(new Function<String,R>(){

			public R call(String v1) throws Exception {
				// TODO Auto-generated method stub
				return null;
			}});
	}
}
