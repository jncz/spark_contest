package com.spss.ibm.spark.data;

import java.util.Random;

import com.spss.ibm.spark.common.RandomNumber;

public class Topic {
	private static final String[] TOPICS = { "中国泡菜攻陷韩国", "娄艺潇 邓家佳", "爸爸去哪儿3", "峰绍冯妮倪",
			"张馨予 删博", "小学恋情死于换位置", "吴倩", "王思聪", "胖不是因为你吃得多", "学校2015", "吴昕",
			"在校生30例艾滋病", "八掌柜", "要不是因为丑", "熊乃瑾", "留长发还是短发", "女生的怪癖", "3分钟数完",
			"蒲公英的正确玩法", "我赚外国人的钱" };
	
	public static String getRadomTopic(Random r, int bound) {
		int index = RandomNumber.getRandomIndex(r, bound);
		return TOPICS[index];
	}
}
