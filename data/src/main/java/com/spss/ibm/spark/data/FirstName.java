package com.spss.ibm.spark.data;

import com.spss.ibm.spark.common.RandomNumber;

public class FirstName {
	private static final String[] FIRST_NAME = { "李", "王", "张", "刘", "陈", "杨", "赵",
			"黄", "周", "吴", "徐", "孙", "胡", "朱", "高", "林", "何", "郭", "马", "罗",
			"梁", "宋", "郑", "谢", "韩", "唐", "冯", "于", "董", "萧", "程", "曹", "袁",
			"邓", "许", "傅", "沈", "曾", "彭", "吕", "苏", "卢", "蒋", "蔡", "贾", "丁",
			"魏", "薛", "叶", "阎", "余", "潘", "杜", "戴", "夏", "锺", "汪", "田", "任",
			"姜", "范", "方", "石", "姚", "谭", "廖", "邹", "熊", "金", "陆", "郝", "孔",
			"白", "崔", "康", "毛", "邱", "秦", "江", "史", "顾", "侯", "邵", "孟", "龙",
			"万", "段", "雷", "钱", "汤", "尹", "黎", "易", "常", "武", "乔", "贺", "赖",
			"龚", "文" };

	public static String getRandomFirstName() {
		int index = RandomNumber.getRandomIndex(FIRST_NAME.length - 1);
		return FIRST_NAME[index];
	}
}
