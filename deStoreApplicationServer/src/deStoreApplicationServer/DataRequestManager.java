package deStoreApplicationServer;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Connects to the data server and makes requests
 */
public class DataRequestManager {
	private Connection conn;
	
	public DataRequestManager() {
		try {
			System.out.println("Connecting to data server");
			Class.forName("com.mysql.cj.jdbc.Driver");
			AppServerConfig config = new AppServerConfig();
			conn = DriverManager.getConnection("jdbc:mysql://" + config.getDataServerIp() + ":" + config.getDataServerPort() + "/destore?user=root&password="+config.getDataServerPassword());
			System.out.println("connected to data server");
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
}
