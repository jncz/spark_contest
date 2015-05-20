package com.spss.ibm.spark.common;

import java.io.File;

public interface MonitorCallBack {
	public void handleNewFiles(File[] files);
}
