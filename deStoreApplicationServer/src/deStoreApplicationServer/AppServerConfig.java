package deStoreApplicationServer;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

/**
 * Parses the application config properties
 */
public class AppServerConfig {
	private JSONParser parser;
	private String appServerPort;
	private String dataServerIp;
	private String dataServerPort;
	private String dataServerPassword;
	
	public AppServerConfig() {
		parser = new JSONParser();
		try {
			JSONObject jsonConfig = (JSONObject) parser.parse(new FileReader("./src/config/config.json"));
			appServerPort = (String) jsonConfig.get("appServerPort");
			dataServerIp = (String) jsonConfig.get("dataServerIp");
			dataServerPort = (String) jsonConfig.get("dataServerPort");
			dataServerPassword = (String) jsonConfig.get("dataServerPassword");

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
			
	}
	
	public String getAppServerPort() {
		return appServerPort;
	}
	
	public String getDataServerIp() {
		return dataServerIp;
	}
	
	public String getDataServerPort() {
		return dataServerPort;
	}
	
	public String getDataServerPassword() {
		return dataServerPassword;
	}
}
