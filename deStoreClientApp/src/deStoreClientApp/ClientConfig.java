package deStoreClientApp;
import java.io.*;
import org.json.simple.*;
import org.json.simple.parser.*;

public class ClientConfig {
	private JSONParser parser;
	private String appServerIp;
	private String appServerPort;
	
	public ClientConfig() {
		parser = new JSONParser();
		try {
			JSONObject jsonConfig = (JSONObject) parser.parse(new FileReader("./src/config/config.json"));
			appServerIp = (String) jsonConfig.get("appServerIp");
			appServerPort = (String) jsonConfig.get("appServerPort");

		}
		catch(Exception e) {
			System.out.println(e.getMessage());
			e.printStackTrace();
		}
	}
	
	public String getAppServerIp() {
		return appServerIp;
	}
	
	public String getAppServerPort() {
		return appServerPort;
	}
}
