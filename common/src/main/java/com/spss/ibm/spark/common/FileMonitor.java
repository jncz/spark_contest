package com.spss.ibm.spark.common;

import java.io.File;

public class FileMonitor {

	public void start(MonitorCallBack callBack) {
		File srcfolder = new File(Config.getRawDataPath());
		File profolder = new File(Config.getProcessingFolderPath());
		File donefolder = new File(Config.getDoneFolderPath());
		moveFiles(srcfolder, profolder);
		callBack.handleNewFiles();
		moveFiles(profolder, donefolder);
	}

	private void moveFiles(File srcfolder, File tarfolder) {
		File[] files = srcfolder.listFiles();
		for (File file : files) {
			file.renameTo(new File(tarfolder, file.getName()));
		}
	}
}
