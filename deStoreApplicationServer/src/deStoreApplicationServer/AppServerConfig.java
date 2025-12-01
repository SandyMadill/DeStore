package deStoreApplicationServer;
import java.io.*;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

/**
 * Parses the application config properties
 */
public class AppServerConfig {
	private static String fileLocation = "./src/config/config.json";
	
	
	private static JSONObject loadJsonConfig() throws FileNotFoundException, IOException, ParseException {
		return (JSONObject) new JSONParser().parse(new FileReader(fileLocation));
	}
	
	private static void saveJsonConfig(JSONObject jsonConfig) throws IOException {
		FileWriter file = new FileWriter(fileLocation);
		file.write(jsonConfig.toJSONString());
		file.close();
	}
	
	public static String getDataServerIp() throws FileNotFoundException, IOException, ParseException {
		return (String) loadJsonConfig().get("dataServerIp");
	}
	
	public static String getDataServerPort() throws FileNotFoundException, IOException, ParseException {
		return (String) loadJsonConfig().get("dataServerPort");
	}
	
	public static String getDataServerPassword() throws FileNotFoundException, IOException, ParseException {
		return (String) loadJsonConfig().get("dataServerPassword");
	}
	
	public static String getAppServerPort() throws FileNotFoundException, IOException, ParseException {
		return (String) loadJsonConfig().get("appServerPort");
	}
	
	public static void setDataServerIp(String dataServerIp) throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonConfig = loadJsonConfig();
		jsonConfig.put("dataServerIp", dataServerIp);
		saveJsonConfig(jsonConfig);
		
	}
	
	public static void setDataServerPort(String dataServerPort) throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonConfig = loadJsonConfig();
		jsonConfig.put("dataServerPort", dataServerPort);
		saveJsonConfig(jsonConfig);
		
	}
	
	public static void setDataServerPassword(String dataServerPassword) throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonConfig = loadJsonConfig();
		jsonConfig.put("dataServerPassword", dataServerPassword);
		saveJsonConfig(jsonConfig);
		
	}
	
	public static void setAppServerPort(String appServerPort) throws FileNotFoundException, IOException, ParseException {
		JSONObject jsonConfig = loadJsonConfig();
		jsonConfig.put("appServerPort", appServerPort);
		saveJsonConfig(jsonConfig);
		
	}
}
