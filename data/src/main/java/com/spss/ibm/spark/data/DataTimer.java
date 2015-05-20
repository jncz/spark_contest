package com.spss.ibm.spark.data;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

public class DataTimer {
	private Timer timer;
	private int seconds;
	private String folder;
	private int total = 0;
	private int bound = 1;
	private int delta = 2;
	private int deltacount = 1;

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
		Random r = new Random(20);
		for (int i = 0; i < 2000; i++) {
			total += 1;
			if (total > (4000 * bound) && total < 80000 && bound < 19) {
				bound += 1;
			}
			if (total > 80000 * deltacount) {
				deltacount +=1;
				delta += 2;
			}
			String raw = Topic.getRadomTopic(r, bound, delta) + ","
					+ FirstName.getRandomFirstName() + ","
					+ System.currentTimeMillis() + "\n";
			buffer.append(raw);
		}
		return buffer.toString();
	}

}
