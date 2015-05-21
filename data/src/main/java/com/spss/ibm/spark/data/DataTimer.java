package com.spss.ibm.spark.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

public class DataTimer {
	private Timer timer;
	private int seconds;
	private String folder;

	public DataTimer(String outputFolder, int seconds) {
		this.timer = new Timer();
		this.seconds = seconds;
		this.folder = outputFolder;
	}

	public void start() {
		timer.schedule(new TimerTask() {

			@Override
			public void run() {
				execution();
			}

		}, seconds * 1000, seconds * 1000);
	}
	
	private void execution() {
		String content = generateFileContent();
		try {
			File file = new File(folder, System.currentTimeMillis() + ".csv");
			FileWriter fw = new FileWriter(file, true);
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
