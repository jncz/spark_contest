package com.spss.ibm.spark.core;

import java.util.Arrays;

import org.apache.spark.SparkConf;
import org.apache.spark.api.java.function.FlatMapFunction;
import org.apache.spark.api.java.function.Function2;
import org.apache.spark.api.java.function.PairFunction;
import org.apache.spark.storage.StorageLevel;
import org.apache.spark.streaming.Durations;
import org.apache.spark.streaming.api.java.JavaDStream;
import org.apache.spark.streaming.api.java.JavaPairDStream;
import org.apache.spark.streaming.api.java.JavaReceiverInputDStream;
import org.apache.spark.streaming.api.java.JavaStreamingContext;

import scala.Tuple2;

public class Test {

	public static void main(String[] args) {
		SparkConf conf = new SparkConf().setMaster("local[2]").setAppName(
				"NetworkWordCount2");
		JavaStreamingContext jssc = new JavaStreamingContext(conf,
				Durations.seconds(10));

		JavaReceiverInputDStream<String> lines = jssc.socketTextStream(
				"localhost", 9999);

		JavaDStream<String> words = lines
				.flatMap(new FlatMapFunction<String, String>() {
					public Iterable<String> call(String x) {
						return Arrays.asList(x.split(" "));
					}
				});

		JavaPairDStream<String, Integer> pairs = words
				.mapToPair(new PairFunction<String, String, Integer>() {
					public Tuple2<String, Integer> call(String s)
							throws Exception {
						return new Tuple2<String, Integer>(s, 1);
					}
				});
		JavaPairDStream<String, Integer> wordCounts = pairs
				.reduceByKey(new Function2<Integer, Integer, Integer>() {
					public Integer call(Integer i1, Integer i2)
							throws Exception {
						return i1 + i2;
					}
				});

		// Print the first ten elements of each RDD generated in this DStream to
		// the console
		wordCounts.persist(StorageLevel.MEMORY_AND_DISK_SER()).cache().print();
		
		jssc.start();
		jssc.awaitTermination();
	}
}
