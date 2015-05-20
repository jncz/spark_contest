package com.spss.ibm.spark.data;

import java.io.IOException;

import com.spss.ibm.spark.common.Config;

public class Boot {
	public static void main(String[] args) throws IOException, InterruptedException {
		DataTimer dt = new DataTimer(Config.getRawDataPath(), 1);
		dt.start();
	}

}
