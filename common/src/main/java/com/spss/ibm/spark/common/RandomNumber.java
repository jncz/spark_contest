package com.spss.ibm.spark.common;

import java.util.Random;

public class RandomNumber {
	public static int getRandomIndex(int length) {
		Random r = new Random(length);
		return r.nextInt(length);
	}

	public static void main(String[] args) {
		getRandomIndex(20);
	}
}
