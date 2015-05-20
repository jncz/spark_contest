package com.spss.ibm.spark.data;

import java.io.IOException;

import com.spss.ibm.spark.common.Config;

public class Boot {
	public static void main(String[] args) throws IOException, InterruptedException {
		Config.load();
		DataTimer dt = new DataTimer(Config.getProperty("data.folder"), 1);
		dt.start();
	}

}
