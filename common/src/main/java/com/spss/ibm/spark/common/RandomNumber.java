package com.spss.ibm.spark.common;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Random;
import java.util.Set;

public class RandomNumber {
	public static int getRandomIndex(int length) {
		double random = Math.random();
		float index = (float) random * length;
		return Math.round(index);
	}

	public static int getRandomIndex(Random r, int bound) {
		return r.nextInt(bound);
	}

	public static void main(String[] args) {
		int length = 10000;
		List<Integer> list = new ArrayList<Integer>();
		Map<Integer, Integer> map = new HashMap<Integer, Integer>();
		Random r = new Random();
		for (int i = 0; i < length; i++) {
			list.add(getRandomIndex(r, 20));
			Integer t = map.get(list.get(i));
			if (t == null) {
				map.put(list.get(i), 1);
			} else {
				int t2 = t + 1;
				map.put(list.get(i), t2);
			}
		}
		
		Set<Entry<Integer, Integer>> sets = map.entrySet();
		for (Entry<Integer, Integer> set : sets) {
			System.out.println(set.getKey() + ":" + set.getValue());
		}
		
	}
}
