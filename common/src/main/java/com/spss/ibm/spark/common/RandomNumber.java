package com.spss.ibm.spark.common;

public class RandomNumber {
	public static int getRandomIndex(int length) {
		double random = Math.random();
		float index = (float) random * length;
		return Math.round(index);
	}
}
