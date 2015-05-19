package com.spss.ibm.spark.common;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardWatchEventKinds;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

public class FileMonitor {
	public static void main(String[] args) {
		Config.load();
		try {
			monitor();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public static void monitor() throws IOException, InterruptedException {
		Path dataFolder = Paths.get(Config.getProperty("data.folder"));
		WatchService ws = FileSystems.getDefault().newWatchService();
		dataFolder.register(ws, StandardWatchEventKinds.ENTRY_CREATE);

		boolean valid = true;
		do {
			WatchKey watchKey = ws.take();

			for (WatchEvent<?> event : watchKey.pollEvents()) {
				if (StandardWatchEventKinds.ENTRY_CREATE.equals(event.kind())) {
					String fileName = event.context().toString();
					// TODO this can notify core project to run some task
					System.out.println("File Created:" + fileName);
				}
			}
			valid = watchKey.reset();

		} while (valid);
	}
}
