package deStoreClientApp;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ClientConfig {
	private static String fileLocation = "./src/config/config.json";
	
	
	private static JSONObject loadJsonConfig() throws FileNotFoundException, IOException, ParseException {
		return (JSONObject) new JSONParser().parse(new FileReader(fileLocation));
	}
	
	private static void saveJsonConfig(JSONObject jsonConfig) throws IOException {
		FileWriter file = new FileWriter(fileLocation);
		file.write(jsonConfig.toJSONString());
		file.close();
	}
	
	public static String getAppServerIp() throws FileNotFoundException, IOException, ParseException {
		return (String) loadJsonConfig().get("appServerIp");
	}
	
	public static String getAppServerPort() throws FileNotFoundException, IOException, ParseException {
		return (String) loadJsonConfig().get("appServerPort");
	}
	
	public static void setAppServerIp(String appServerIp) throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonConfig = loadJsonConfig();
		jsonConfig.put("appServerIp", appServerIp);
		saveJsonConfig(jsonConfig);
		
	}
	
	public static void setAppServerPort(String appServerPort) throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonConfig = loadJsonConfig();
		jsonConfig.put("appServerPort", appServerPort);
		saveJsonConfig(jsonConfig);
		
	}
	
	
}
