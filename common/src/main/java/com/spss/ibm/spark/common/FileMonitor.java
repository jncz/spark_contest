package com.spss.ibm.spark.common;

import java.io.File;

public class FileMonitor {
	public static void main(String[] args) {
		Config.load();
		FileMonitor fm = new FileMonitor();
		fm.monitor(new MonitorCallBack() {

			@Override
			public void handleNewFiles(File[] files) {
				for (File file : files)
					System.out.println("File Created:" + file.getName());
			}
		});
	}

	public void monitor(MonitorCallBack callBack) {
		File srcfolder = new File(Config.getProperty("data.folder"));
		File tgfolder = new File(Config.getProperty("done.folder"));
		File[] files = srcfolder.listFiles();
		if (files != null && files.length > 0) {
			callBack.handleNewFiles(files);
		}
		for (File file : files) {
			file.renameTo(new File(tgfolder, file.getName()));
		}
	}
}
