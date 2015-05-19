package com.spss.ibm.spark.data;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DataTimer {
	private Timer timer;

	public DataTimer(int seconds) {
		timer = new Timer();
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				execution();
			}

		}, seconds * 1000, seconds * 1000);
	}

	private void execution() {
		String content = generateFileContent();
		String rawFolder = "/home/parallels/EricWorkspaces/Works/HackSpark/spark_contest/common/src/main/resources/Sample/raw/";
		try {
			FileWriter fw = new FileWriter(rawFolder
					+ System.currentTimeMillis() + ".csv", true);
			fw.write(content);
			fw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String generateFileContent() {
		StringBuffer buffer = new StringBuffer();
		for (int i = 0; i < 1000; i++) {
			String raw = Topic.getRadomTopic() + ","
					+ FirstName.getRandomFirstName() + ","
					+ System.currentTimeMillis() + "\n";
			buffer.append(raw);
		}
		return buffer.toString();
	}

}
