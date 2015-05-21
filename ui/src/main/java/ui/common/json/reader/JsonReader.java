package ui.common.json.reader;

import java.io.File;

import org.json.simple.JSONObject;

import ui.common.comet.MessageSender;
import ui.common.json.parser.JsonParser;

public class JsonReader {
	private MessageSender messageSender;
	private final String jsonPath = "/home/liping/Downloads/data/output/latest.json";

	public JsonReader(MessageSender messageSender) {
		this.messageSender = messageSender;
	}

	public void start() {
		Runnable r = new Runnable() {

			public void run() {
				int counter = 1;
				while (true) {
					File jsonFile = new File(jsonPath);
//					File outputFolder = new File(jsonPath);
//					File[] files = outputFolder.listFiles();
//					int i = (int) (Math.round(Math.random()) * 10);
					if(jsonFile.exists()){
						JSONObject object = JsonParser.parseJsonFile(jsonPath);
						messageSender.send(object.toJSONString());
						System.out.println(counter);
						counter ++;
					}
					try {
						Thread.sleep(5000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		Thread t = new Thread(r);
		t.start();
	}
}
