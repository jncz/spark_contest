package ui.common.json.parser;

import java.io.FileReader;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class JsonParser {
	public static JSONObject parseJsonFile(String jsonPath) {
		JSONObject jsonObject = null;
		JSONParser parser = new JSONParser();
		try {
			Object obj = parser.parse(new FileReader(jsonPath));
			jsonObject = (JSONObject) obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return jsonObject;
	}

}
